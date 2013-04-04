package net.sf.anathema.character.impl.model.creation.bonus.virtue;

import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.impl.model.advance.models.AbstractSpendingModel;

public class VirtueBonusModel extends AbstractSpendingModel {
  private final VirtueCostCalculator virtueCalculator;
  private final ICreationPoints creationPoints;

  public VirtueBonusModel(VirtueCostCalculator virtueCalculator, ICreationPoints creationPoints) {
    super("Advantages", "Virtues");
    this.virtueCalculator = virtueCalculator;
    this.creationPoints = creationPoints;
  }

  @Override
  public Integer getValue() {
    return virtueCalculator.getVirtueDotsSpent();
  }

  @Override
  public int getSpentBonusPoints() {
    return virtueCalculator.getBonusPointsSpent();
  }

  @Override
  public int getAlotment() {
    return creationPoints.getVirtueCreationPoints();
  }
}