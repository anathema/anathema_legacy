package net.sf.anathema.character.impl.model.advance.models;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.impl.model.advance.IPointCostCalculator;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
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
    for (ICharm charm : charmConfiguration.getLearnedCharms(true)) {
      int charmCosts = calculateCharmCost(charmConfiguration, charm);
      if (charmConfiguration.isAlienCharm(charm)) {
        charmCosts *= 2;
      }
      experienceCosts += charmCosts;
    }
    return experienceCosts;
  }

  private int calculateCharmCost(ICharmConfiguration charmConfiguration, ICharm charm) {
    ISpecialCharmConfiguration specialCharm = charmConfiguration.getSpecialCharmConfiguration(charm);
    int charmCost = calculator.getCharmCosts(
        charm,
        basicCharacter,
        traitConfiguration,
        statistics.getCharacterTemplate().getMagicTemplate().getFavoringTraitType());
    if (specialCharm != null) {
      int timesLearnedWithExperience = specialCharm.getCurrentLearnCount() - specialCharm.getCreationLearnCount();
      return timesLearnedWithExperience * charmCost;
    }
    else if (charmConfiguration.getGroup(charm).isLearned(charm, true)) {
      return charmCost;
    }
    return 0;
  }
}