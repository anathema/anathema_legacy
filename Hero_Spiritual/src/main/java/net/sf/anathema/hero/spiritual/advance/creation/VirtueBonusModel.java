package net.sf.anathema.hero.spiritual.advance.creation;

import net.sf.anathema.character.main.template.creation.ICreationPoints;
import net.sf.anathema.hero.advance.overview.model.AbstractSpendingModel;

public class VirtueBonusModel extends AbstractSpendingModel {
  private final VirtueBonusCostCalculator virtueCalculator;
  private final ICreationPoints creationPoints;

  public VirtueBonusModel(VirtueBonusCostCalculator virtueCalculator, ICreationPoints creationPoints) {
    super("Spiritual", "Virtues");
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
  public int getAllotment() {
    return creationPoints.getVirtueCreationPoints();
  }
}