package net.sf.anathema.character.impl.model.creation.bonus.ability;

import net.sf.anathema.character.presenter.overview.ISpendingModel;

public class DefaultAbilityBonusModel implements ISpendingModel {
  private final AbilityCostCalculator abilityCalculator;

  public DefaultAbilityBonusModel(AbilityCostCalculator abilityCalculator) {
    this.abilityCalculator = abilityCalculator;
  }

  public Integer getValue() {
    return abilityCalculator.getFreePointsSpent(false);
  }

  public int getSpentBonusPoints() {
    return abilityCalculator.getBonusPointsSpent();
  }

  public String getId() {
    return "DefaultAbilities"; //$NON-NLS-1$
  }
}
