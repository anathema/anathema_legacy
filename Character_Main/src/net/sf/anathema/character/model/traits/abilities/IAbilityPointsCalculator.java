package net.sf.anathema.character.model.traits.abilities;

public interface IAbilityPointsCalculator {
  int calculateAbilityPoints(boolean favored);

  int calculateBonusPoints();

  int calculateSpecialtyBonusPoints();
}