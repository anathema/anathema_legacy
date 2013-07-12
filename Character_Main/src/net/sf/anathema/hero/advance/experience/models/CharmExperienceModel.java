package net.sf.anathema.hero.advance.experience.models;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.special.CharmSpecialsModel;
import net.sf.anathema.character.main.magic.model.charm.special.IUpgradableCharmConfiguration;
import net.sf.anathema.character.main.magic.model.charm.special.SubEffectCharmSpecials;
import net.sf.anathema.hero.advance.AbstractIntegerValueModel;
import net.sf.anathema.hero.advance.experience.PointCostCalculator;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.model.Hero;

import java.util.HashSet;
import java.util.Set;

public class CharmExperienceModel extends AbstractIntegerValueModel {

  private final PointCostCalculator calculator;
  private final Hero hero;

  public CharmExperienceModel(PointCostCalculator calculator, Hero hero) {
    super("Experience", "Charms");
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
    Set<Charm> charmsCalculated = new HashSet<>();
    for (Charm charm : charmConfiguration.getLearnedCharms(true)) {
      int charmCosts = calculateCharmCost(charmConfiguration, charm, charmsCalculated);
      if (charmConfiguration.isAlienCharm(charm)) {
        charmCosts *= 2;
      }
      experienceCosts += charmCosts;
      charmsCalculated.add(charm);
    }
    return experienceCosts;
  }

  private int calculateCharmCost(CharmsModel charmConfiguration, Charm charm, Set<Charm> charmsCalculated) {
    CharmSpecialsModel specialCharm = charmConfiguration.getCharmSpecialsModel(charm);
    int charmCost = calculator.getCharmCosts(hero, charm);
    if (specialCharm != null) {
      int timesLearnedWithExperience = specialCharm.getCurrentLearnCount() - specialCharm.getCreationLearnCount();
      int specialCharmCost = timesLearnedWithExperience * charmCost;
      if (specialCharm instanceof IUpgradableCharmConfiguration) {
        return (costsExperience(charmConfiguration, charm, charmsCalculated) ? charmCost : 0) +
               ((IUpgradableCharmConfiguration) specialCharm).getUpgradeXPCost();
      }
      if (!(specialCharm instanceof SubEffectCharmSpecials)) {
        return specialCharmCost;
      }
      SubEffectCharmSpecials subeffectCharmConfiguration = (SubEffectCharmSpecials) specialCharm;
      int count = Math.max(0, (subeffectCharmConfiguration.getExperienceLearnedSubEffectCount() -
                               (subeffectCharmConfiguration.getCreationLearnedSubEffectCount() == 0 ? 1 : 0)));
      int subeffectCost = (int) Math.ceil(count * subeffectCharmConfiguration.getPointCostPerEffect() * 2);
      return subeffectCost + specialCharmCost;
    }
    return costsExperience(charmConfiguration, charm, charmsCalculated) ? charmCost : 0;
  }

  private boolean costsExperience(CharmsModel charmConfiguration, Charm charm, Set<Charm> charmsCalculated) {
    if (charmConfiguration.getGroup(charm).isLearned(charm, true)) {
      for (Charm mergedCharm : charm.getMergedCharms()) {
        if (charmsCalculated.contains(mergedCharm) && !isSpecialCharm(charm)) {
          return false;
        }
      }
      return true;
    }
    return false;
  }

  private boolean isSpecialCharm(Charm charm) {
    return CharmsModelFetcher.fetch(hero).getCharmSpecialsModel(charm) != null;
  }
}