package net.sf.anathema.character.generic.impl.additional;

import net.sf.anathema.character.generic.additionalrules.ITraitCostModifier;

public class DefaultTraitCostModifier implements ITraitCostModifier {

  @Override
  public int getAdditionalDotsToSpend(int traitValue) {
    return 0;
  }

  @Override
  public int getAdditionalBonusPointsToSpend(int traitValue) {
    return 0;
  }
}
