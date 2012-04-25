package net.sf.anathema.character.impl.model.creation.bonus.backgrounds;

import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.impl.model.advance.models.AbstractSpendingModel;

public class BackgroundBonusModel extends AbstractSpendingModel {

  private final BackgroundBonusPointCostCalculator backgroundCalculator;
  private final ICreationPoints creationPoints;

  public BackgroundBonusModel(BackgroundBonusPointCostCalculator backgroundCalculator, ICreationPoints creationPoints) {
    super("Advantages", "Backgrounds"); //$NON-NLS-1$ //$NON-NLS-2$
    this.backgroundCalculator = backgroundCalculator;
    this.creationPoints = creationPoints;
  }

  @Override
  public Integer getValue() {
    return backgroundCalculator.getSpentDots();
  }

  @Override
  public int getSpentBonusPoints() {
    return backgroundCalculator.getBonusPointSpent();
  }

  @Override
  public int getAlotment() {
    return creationPoints.getBackgroundPointCount();
  }
}