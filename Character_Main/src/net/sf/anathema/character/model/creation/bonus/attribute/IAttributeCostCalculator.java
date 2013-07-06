package net.sf.anathema.character.model.creation.bonus.attribute;

public interface IAttributeCostCalculator {

  void recalculate();

  int getBonusPointCost();
}