package net.sf.anathema.character.impl.model.creation.bonus.virtue;

import net.sf.anathema.character.presenter.overview.ISpendingModel;

public class VirtueBonusModel implements ISpendingModel {
  private final VirtueCostCalculator virtueCalculator;

  public VirtueBonusModel(VirtueCostCalculator virtueCalculator) {
    this.virtueCalculator = virtueCalculator;
  }

  public Integer getValue() {
    return virtueCalculator.getVirtueDotsSpent();
  }

  public int getSpentBonusPoints() {
    return virtueCalculator.getBonusPointsSpent();
  }

  public String getId() {
    return "Virtues"; //$NON-NLS-1$
  }
}