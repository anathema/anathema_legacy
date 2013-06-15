package net.sf.anathema.character.generic.additionaltemplate;

public interface IAdditionalModelBonusPointCalculator {

  void recalculate();

  int getBonusPointCost();

  int getBonusPointsGranted();
}