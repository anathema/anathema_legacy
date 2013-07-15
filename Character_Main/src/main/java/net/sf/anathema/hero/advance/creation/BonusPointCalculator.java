package net.sf.anathema.hero.advance.creation;

import net.sf.anathema.hero.points.HeroBonusPointCalculator;

import java.util.ArrayList;
import java.util.List;

public class BonusPointCalculator {

  private final List<HeroBonusPointCalculator> allCalculators = new ArrayList<>();

  public void addBonusPointCalculator(HeroBonusPointCalculator additionalCalculator) {
    allCalculators.add(additionalCalculator);
  }

  public void recalculate() {
    for (HeroBonusPointCalculator calculator : allCalculators) {
      calculator.recalculate();
    }
  }

  public int getAdditionalGeneralBonusPoints() {
    int additionalGranted = 0;
    for (HeroBonusPointCalculator calculator : allCalculators) {
      additionalGranted += calculator.getBonusPointsGranted();
    }
    return additionalGranted;
  }

  public int getTotalValue() {
    int pointsSpent = 0;
    for (HeroBonusPointCalculator calculator : allCalculators) {
      pointsSpent += calculator.getBonusPointCost();
    }
    return pointsSpent;
  }
}