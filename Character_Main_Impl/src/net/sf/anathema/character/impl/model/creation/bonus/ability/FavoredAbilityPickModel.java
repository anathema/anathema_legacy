package net.sf.anathema.character.impl.model.creation.bonus.ability;

import net.sf.anathema.character.presenter.overview.ISpendingModel;

public class FavoredAbilityPickModel implements ISpendingModel {

  private final AbilityCostCalculator abilityCalculator;

  public FavoredAbilityPickModel(AbilityCostCalculator abilityCalculator) {
    this.abilityCalculator = abilityCalculator;
  }

  public int getSpentBonusPoints() {
    return 0;
  }

  public Integer getValue() {
    return abilityCalculator.getFavoredPicksSpent();
  }

  public String getId() {
    return "FavoredAbilityPick"; //$NON-NLS-1$
  }
}