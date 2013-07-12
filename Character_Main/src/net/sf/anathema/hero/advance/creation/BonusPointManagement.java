package net.sf.anathema.hero.advance.creation;

import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.points.HeroBonusPointCalculator;
import net.sf.anathema.hero.points.PointModelFetcher;
import net.sf.anathema.hero.points.overview.SpendingModel;

public class BonusPointManagement implements IBonusPointManagement {

  private final BonusPointCalculator bonusPointCalculator = new BonusPointCalculator();
  private final SpendingModel totalModel;

  public BonusPointManagement(Hero hero) {
    for (HeroBonusPointCalculator additionalCalculator : PointModelFetcher.fetch(hero).getBonusPointCalculators()) {
      bonusPointCalculator.addBonusPointCalculator(additionalCalculator);
    }
    this.totalModel = new TotalBonusPointModel(hero.getTemplate().getCreationPoints(), bonusPointCalculator);
  }

  @Override
  public void recalculate() {
    bonusPointCalculator.recalculate();
  }

  @Override
  public SpendingModel getTotalModel() {
    return totalModel;
  }

  @Override
  public String getSummaryCategory() {
    return TotalBonusPointModel.SUMMARY_CATEGORY_ID;
  }
}