package net.sf.anathema.character.generic.additionalrules;

public interface ITraitCostModifier {

  public int getAdditionalDotsToSpend(int traitValue);

  public int getAdditionalBonusPointsToSpend(int traitValue);
}