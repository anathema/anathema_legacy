package net.sf.anathema.character.impl.model.advance.models;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.impl.model.advance.IPointCostCalculator;
import net.sf.anathema.character.main.model.traits.TraitMap;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.charm.CharmModel;
import net.sf.anathema.character.model.charm.special.ISubeffectCharmConfiguration;
import net.sf.anathema.character.model.charm.special.IUpgradableCharmConfiguration;

import java.util.HashSet;
import java.util.Set;

public class CharmExperienceModel extends AbstractIntegerValueModel {
  private final TraitMap traitConfiguration;
  private final IPointCostCalculator calculator;
  private final ICharacter character;

  public CharmExperienceModel(TraitMap traitConfiguration, IPointCostCalculator calculator, ICharacter character) {
    super("Experience", "Charms");
    this.traitConfiguration = traitConfiguration;
    this.calculator = calculator;
    this.character = character;
  }

  @Override
  public Integer getValue() {
    return getCharmCosts();
  }

  private int getCharmCosts() {
    int experienceCosts = 0;
    CharmModel charmConfiguration = character.getCharms();
    Set<ICharm> charmsCalculated = new HashSet<>();
    for (ICharm charm : charmConfiguration.getLearnedCharms(true)) {
      int charmCosts = calculateCharmCost(charmConfiguration, charm, charmsCalculated);
      if (charmConfiguration.isAlienCharm(charm)) {
        charmCosts *= 2;
      }
      experienceCosts += charmCosts;
      charmsCalculated.add(charm);
    }
    return experienceCosts;
  }

  private int calculateCharmCost(CharmModel charmConfiguration, ICharm charm, Set<ICharm> charmsCalculated) {
    ISpecialCharmConfiguration specialCharm = charmConfiguration.getSpecialCharmConfiguration(charm);
    int charmCost = calculator.getCharmCosts(character, charm, traitConfiguration);
    if (specialCharm != null) {
      int timesLearnedWithExperience = specialCharm.getCurrentLearnCount() - specialCharm.getCreationLearnCount();
      int specialCharmCost = timesLearnedWithExperience * charmCost;
      if (specialCharm instanceof IUpgradableCharmConfiguration) {
        return (costsExperience(charmConfiguration, charm, charmsCalculated) ? charmCost : 0) +
               ((IUpgradableCharmConfiguration) specialCharm).getUpgradeXPCost();
      }
      if (!(specialCharm instanceof ISubeffectCharmConfiguration)) {
        return specialCharmCost;
      }
      ISubeffectCharmConfiguration subeffectCharmConfiguration = (ISubeffectCharmConfiguration) specialCharm;
      int count = Math.max(0, (subeffectCharmConfiguration.getExperienceLearnedSubeffectCount() -
                               (subeffectCharmConfiguration.getCreationLearnedSubeffectCount() == 0 ? 1 : 0)));
      int subeffectCost = (int) Math.ceil(count * subeffectCharmConfiguration.getPointCostPerEffect() * 2);
      return subeffectCost + specialCharmCost;
    }
    return costsExperience(charmConfiguration, charm, charmsCalculated) ? charmCost : 0;
  }

  private boolean costsExperience(CharmModel charmConfiguration, ICharm charm, Set<ICharm> charmsCalculated) {
    if (charmConfiguration.getGroup(charm).isLearned(charm, true)) {
      for (ICharm mergedCharm : charm.getMergedCharms()) {
        if (charmsCalculated.contains(mergedCharm) && !isSpecialCharm(charm)) {
          return false;
        }
      }
      return true;
    }
    return false;
  }

  private boolean isSpecialCharm(ICharm charm) {
    return character.getCharms().getSpecialCharmConfiguration(charm) != null;
  }
}