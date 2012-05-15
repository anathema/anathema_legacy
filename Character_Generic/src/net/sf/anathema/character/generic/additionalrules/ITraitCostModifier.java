package net.sf.anathema.character.generic.additionalrules;

public interface ITraitCostModifier {

  int getAdditionalDotsToSpend(int traitValue);

  int getAdditionalBonusPointsToSpend(int traitValue);
}