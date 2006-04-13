package net.sf.anathema.character.impl.model.creation.bonus.backgrounds;

import net.sf.anathema.character.presenter.overview.ISpendingModel;

public class BackgroundBonusModel implements ISpendingModel {

  private final BackgroundBonusPointCostCalculator backgroundCalculator;

  public BackgroundBonusModel(BackgroundBonusPointCostCalculator backgroundCalculator) {
    this.backgroundCalculator = backgroundCalculator;
  }

  public Integer getValue() {
    return backgroundCalculator.getSpentDots();
  }

  public int getSpentBonusPoints() {
    return backgroundCalculator.getBonusPointSpent();
  }

  public String getId() {
    return "Backgrounds"; //$NON-NLS-1$
  }
}