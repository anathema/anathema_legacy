package net.sf.anathema.character.generic.additionaltemplate;

public interface HeroModelBonusPointCalculator {

  void recalculate();

  int getBonusPointCost();

  int getBonusPointsGranted();
}