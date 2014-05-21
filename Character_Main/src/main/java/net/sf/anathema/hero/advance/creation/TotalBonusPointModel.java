package net.sf.anathema.hero.advance.creation;

import net.sf.anathema.hero.template.creation.ICreationPoints;
import net.sf.anathema.hero.advance.overview.model.AbstractSpendingModel;

public class TotalBonusPointModel extends AbstractSpendingModel {

  public static final String SUMMARY_CATEGORY_ID = "Summary";
  private final ICreationPoints creationPoints;
  private BonusPointCalculator bonusPointCalculator;

  public TotalBonusPointModel(ICreationPoints creationPoints, BonusPointCalculator bonusPointCalculator) {
    super(SUMMARY_CATEGORY_ID, "Total");
    this.bonusPointCalculator = bonusPointCalculator;
    this.creationPoints = creationPoints;
  }

  @Override
  public int getSpentBonusPoints() {
    return 0;
  }

  @Override
  public Integer getValue() {
    return bonusPointCalculator.getTotalValue();
  }

  @Override
  public int getAllotment() {
    return creationPoints.getBonusPointCount() + bonusPointCalculator.getAdditionalGeneralBonusPoints();
  }
}
