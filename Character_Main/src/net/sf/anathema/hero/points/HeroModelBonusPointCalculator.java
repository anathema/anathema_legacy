package net.sf.anathema.hero.points;

public interface HeroModelBonusPointCalculator {

  void recalculate();

  int getBonusPointCost();

  int getBonusPointsGranted();
}