package net.sf.anathema.character.impl.model.advance.models;

import java.util.HashSet;
import java.util.Set;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.impl.model.advance.IPointCostCalculator;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.special.ISubeffectCharmConfiguration;
import net.sf.anathema.character.model.charm.special.IUpgradableCharmConfiguration;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;

public class CharmExperienceModel extends AbstractIntegerValueModel {
  private final ICoreTraitConfiguration traitConfiguration;
  private final IPointCostCalculator calculator;
  private final ICharacterStatistics statistics;
  private final IBasicCharacterData basicCharacter;

  public CharmExperienceModel(
      ICoreTraitConfiguration traitConfiguration,
      IPointCostCalculator calculator,
      ICharacterStatistics statistics,
      IBasicCharacterData basicCharacter) {
    super("Experience", "Charms"); //$NON-NLS-1$//$NON-NLS-2$
    this.traitConfiguration = traitConfiguration;
    this.calculator = calculator;
    this.statistics = statistics;
    this.basicCharacter = basicCharacter;
  }

  public Integer getValue() {
    return getCharmCosts();
  }

  private int getCharmCosts() {
    int experienceCosts = 0;
    ICharmConfiguration charmConfiguration = statistics.getCharms();
    Set<ICharm> charmsCalculated = new HashSet<ICharm>();
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

  private int calculateCharmCost(ICharmConfiguration charmConfiguration, ICharm charm, Set<ICharm> charmsCalculated) {
    ISpecialCharmConfiguration specialCharm = charmConfiguration.getSpecialCharmConfiguration(charm);
    int charmCost = calculator.getCharmCosts(
        charm,
        basicCharacter,
        traitConfiguration,
        statistics.getCharacterTemplate().getMagicTemplate().getFavoringTraitType());
    if (specialCharm != null) {
      int timesLearnedWithExperience = specialCharm.getCurrentLearnCount() - specialCharm.getCreationLearnCount();
      final int specialCharmCost = timesLearnedWithExperience * charmCost;
      if (specialCharm instanceof IUpgradableCharmConfiguration)
    	  return charmCost + ((IUpgradableCharmConfiguration)specialCharm).getUpgradeXPCost();
      if (!(specialCharm instanceof ISubeffectCharmConfiguration)) {
        return specialCharmCost;
      }
      final ISubeffectCharmConfiguration subeffectCharmConfiguration = (ISubeffectCharmConfiguration) specialCharm;
      final int count = Math.max(
          0,
          (subeffectCharmConfiguration.getExperienceLearnedSubeffectCount() - (subeffectCharmConfiguration.getCreationLearnedSubeffectCount() == 0
              ? 1
              : 0)));
      int subeffectCost = (int) Math.ceil(count * subeffectCharmConfiguration.getPointCostPerEffect() * 2);
      return subeffectCost + specialCharmCost;
    }
    else if (charmConfiguration.getGroup(charm).isLearned(charm, true)) {
      for (ICharm mergedCharm : charm.getMergedCharms()) {
        if (charmsCalculated.contains(mergedCharm) && !isSpecialCharm(charm)) {
          return 0;
        }
      }
      return charmCost;
    }
    return 0;
  }
  
  private boolean isSpecialCharm(ICharm charm)
  {
	  return statistics.getCharms().getSpecialCharmConfiguration(charm) != null;
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}