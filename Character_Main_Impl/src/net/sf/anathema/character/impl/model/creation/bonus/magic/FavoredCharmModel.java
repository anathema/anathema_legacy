package net.sf.anathema.character.impl.model.creation.bonus.magic;

import net.sf.anathema.character.presenter.overview.ISpendingModel;

public class FavoredCharmModel implements ISpendingModel {
  private final MagicCostCalculator magicCalculator;

  public FavoredCharmModel(MagicCostCalculator magicCalculator) {
    this.magicCalculator = magicCalculator;
  }

  public int getSpentBonusPoints() {
    return 0;
  }

  public Integer getValue() {
    return magicCalculator.getFavoredCharmPicksSpent();
  }

  public String getId() {
    return "FavoredCharm"; //$NON-NLS-1$
  }
}