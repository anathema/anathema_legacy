package net.sf.anathema.hero.intimacies.points;

import net.sf.anathema.hero.intimacies.model.IntimaciesModel;
import net.sf.anathema.hero.points.HeroBonusPointCalculator;

public class IntimaciesBonusPointCalculator implements HeroBonusPointCalculator {

  private final IntimaciesModel model;
  private int cost;

  public IntimaciesBonusPointCalculator(IntimaciesModel model) {
    this.model = model;
  }

  @Override
  public void recalculate() {
    this.cost = model.getEntries().size() > model.getFreeIntimacies() ? 3 : 0;
  }

  @Override
  public int getBonusPointCost() {
    return cost;
  }

  @Override
  public int getBonusPointsGranted() {
    return 0;
  }
}