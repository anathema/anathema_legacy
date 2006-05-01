package net.sf.anathema.lib.workflow.intvalue;

import net.sf.anathema.lib.data.Range;

public class RangedIntValueModel extends SimpleIntValueModel {

  private final Range range;

  public RangedIntValueModel(Range range) {
    super(range.getLowerBound());
    this.range = range;
  }

  @Override
  public void setValue(int value) {
    super.setValue(range.getNearestValue(value));
  }
}