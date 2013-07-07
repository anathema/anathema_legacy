package net.sf.anathema.character.main.advance.models;

import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.hero.charms.CharmsModel;
import net.sf.anathema.hero.charms.CharmsModelFetcher;
import net.sf.anathema.hero.traits.TraitMap;
import net.sf.anathema.character.main.advance.IPointCostCalculator;
import net.sf.anathema.character.main.charm.special.ISubeffectCharmConfiguration;
import net.sf.anathema.character.main.charm.special.IUpgradableCharmConfiguration;
import net.sf.anathema.hero.model.Hero;

import java.util.HashSet;
import java.util.Set;

public class CharmExperienceModel extends AbstractIntegerValueModel {
  private final TraitMap traitConfiguration;
  private final IPointCostCalculator calculator;
  private final Hero hero;

  public CharmExperienceModel(TraitMap traitConfiguration, IPointCostCalculator calculator, Hero hero) {
    super("Experience", "Charms");
    this.traitConfiguration = traitConfiguration;
    this.calculator = calculator;
    this.hero = hero;
  }

  @Override
  public Integer getValue() {
    return getCharmCosts();
  }

  private int getCharmCosts() {
    int experienceCosts = 0;
    CharmsModel charmConfiguration = CharmsModelFetcher.fetch(hero);
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

  private int calculateCharmCost(CharmsModel charmConfiguration, ICharm charm, Set<ICharm> charmsCalculated) {
    ISpecialCharmConfiguration specialCharm = charmConfiguration.getSpecialCharmConfiguration(charm);
    int charmCost = calculator.getCharmCosts(hero, charm, traitConfiguration);
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

  private boolean costsExperience(CharmsModel charmConfiguration, ICharm charm, Set<ICharm> charmsCalculated) {
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
    return CharmsModelFetcher.fetch(hero).getSpecialCharmConfiguration(charm) != null;
  }
}