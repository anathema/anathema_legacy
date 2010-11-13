package net.sf.anathema.character.generic.impl.additional;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.data.Range;

public class AdditionalRange implements IAdditionalTraitRange {

  private final int additionalMaximum;
  private final int additionalMinimum;
  private final ITraitType type;

  public AdditionalRange(ITraitType type, int additionalMaximum, int additionalMinimum) {
    this.type = type;
    this.additionalMaximum = additionalMaximum;
    this.additionalMinimum = additionalMinimum;
  }

  public void modify(Range range) {
    range.setLowerBound(range.getLowerBound() + additionalMinimum);
    range.setUpperBoundBound(range.getUpperBound() + additionalMaximum);
  }

  public ITraitType getType() {
    return type;
  }
}