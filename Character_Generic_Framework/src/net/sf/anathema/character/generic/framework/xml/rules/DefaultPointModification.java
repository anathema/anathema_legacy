package net.sf.anathema.character.generic.framework.xml.rules;

import net.sf.anathema.character.generic.util.IPointModification;

public class DefaultPointModification implements IPointModification {
  private final int minimumValue;
  private final int multiplier;
  private final int fixedCost;

  public DefaultPointModification(int minimumValue, int multiplier, int fixedCost) {
    this.minimumValue = minimumValue;
    this.multiplier = multiplier;
    this.fixedCost = fixedCost;
  }

  @Override
  public int getAdditionalPoints(int traitValue) {
    int variable = Math.max(0, (traitValue - minimumValue) * multiplier);
    int fixed = traitValue > minimumValue ? fixedCost : 0;
    return variable + fixed;
  }
}