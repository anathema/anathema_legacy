package net.sf.anathema.character.model.traits.abilities;

public interface IAbilityPointsCalculator {
  public int calculateAbilityPoints(boolean favored);

  public int calculateBonusPoints();

  public int calculateSpecialtyBonusPoints();
}