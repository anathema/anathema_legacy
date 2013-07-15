package net.sf.anathema.hero.points;

public interface HeroBonusPointCalculator {

  void recalculate();

  int getBonusPointCost();

  int getBonusPointsGranted();
}