package net.sf.anathema.hero.charms.advance.experience;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.special.CharmSpecialsModel;
import net.sf.anathema.character.main.magic.charm.special.IUpgradableCharmConfiguration;
import net.sf.anathema.character.main.magic.charm.special.SubEffectCharmSpecials;
import net.sf.anathema.hero.advance.overview.model.AbstractIntegerValueModel;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.model.Hero;

import java.util.HashSet;
import java.util.Set;

public class CharmExperienceModel extends AbstractIntegerValueModel {

  private final CharmExperienceCostCalculator calculator;
  private final Hero hero;

  public CharmExperienceModel(CharmExperienceCostCalculator calculator, Hero hero) {
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

  private int calculateCharmCost(CharmsModel charms, Charm charm, Set<Charm> charmsCalculated) {
    CharmSpecialsModel specialCharm = charms.getCharmSpecialsModel(charm);
    int charmCost = calculator.getCharmCosts(hero, charm);
    if (specialCharm != null) {
      int timesLearnedWithExperience = specialCharm.getCurrentLearnCount() - specialCharm.getCreationLearnCount();
      int specialCharmCost = timesLearnedWithExperience * charmCost;
      if (specialCharm instanceof IUpgradableCharmConfiguration) {
        return (costsExperience(charms, charm, charmsCalculated) ? charmCost : 0) +
               ((IUpgradableCharmConfiguration) specialCharm).getUpgradeXPCost();
      }
      if (!(specialCharm instanceof SubEffectCharmSpecials)) {
        return specialCharmCost;
      }
      SubEffectCharmSpecials subEffectCharmConfiguration = (SubEffectCharmSpecials) specialCharm;
      int count = Math.max(0, (subEffectCharmConfiguration.getExperienceLearnedSubEffectCount() -
                               (subEffectCharmConfiguration.getCreationLearnedSubEffectCount() == 0 ? 1 : 0)));
      int subEffectCost = (int) Math.ceil(count * subEffectCharmConfiguration.getPointCostPerEffect() * 2);
      return subEffectCost + specialCharmCost;
    }
    return costsExperience(charms, charm, charmsCalculated) ? charmCost : 0;
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