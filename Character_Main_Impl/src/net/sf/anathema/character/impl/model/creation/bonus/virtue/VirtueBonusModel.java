package net.sf.anathema.character.impl.model.creation.bonus.virtue;

import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.impl.model.advance.models.AbstractSpendingModel;

public class VirtueBonusModel extends AbstractSpendingModel {
  private final VirtueCostCalculator virtueCalculator;
  private final ICreationPoints creationPoints;

  public VirtueBonusModel(VirtueCostCalculator virtueCalculator, ICreationPoints creationPoints) {
    super("Advantages", "Virtues"); //$NON-NLS-1$ //$NON-NLS-2$
    this.virtueCalculator = virtueCalculator;
    this.creationPoints = creationPoints;
  }

  public Integer getValue() {
    return virtueCalculator.getVirtueDotsSpent();
  }

  public int getSpentBonusPoints() {
    return virtueCalculator.getBonusPointsSpent();
  }

  public int getAlotment() {
    return creationPoints.getVirtueCreationPoints();
  }
}