package net.sf.anathema.character.impl.model.creation.bonus.ability;

import net.sf.anathema.character.presenter.overview.ISpendingModel;

public class FavoredAbilityBonusModel implements ISpendingModel {
  private final AbilityCostCalculator abilityCalculator;

  public FavoredAbilityBonusModel(AbilityCostCalculator abilityCalculator) {
    this.abilityCalculator = abilityCalculator;
  }

  public Integer getValue() {
    return abilityCalculator.getFreePointsSpent(true);
  }

  public int getSpentBonusPoints() {
    return 0;
  }

  public String getId() {
    return "FavoredAbilties"; //$NON-NLS-1$
  }
}