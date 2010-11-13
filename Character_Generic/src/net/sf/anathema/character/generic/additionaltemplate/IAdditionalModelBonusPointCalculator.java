package net.sf.anathema.character.generic.additionaltemplate;

public interface IAdditionalModelBonusPointCalculator {

  public void recalculate();

  public int getBonusPointCost();

  public int getBonusPointsGranted();
}