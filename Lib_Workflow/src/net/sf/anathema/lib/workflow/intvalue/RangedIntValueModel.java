package net.sf.anathema.lib.workflow.intvalue;

import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.control.intvalue.IntValueControl;
import net.sf.anathema.lib.data.Range;

public class RangedIntValueModel implements IIntValueModel {

  private final Range range;

  private final IntValueControl intValueControl = new IntValueControl();
  private int value;

  public RangedIntValueModel(int initialValue) {
    this(new Range(Integer.MIN_VALUE, Integer.MAX_VALUE), initialValue);
  }

  public RangedIntValueModel(Range range) {
    this(range, range.getLowerBound());
  }

  public RangedIntValueModel(Range range, int initialValue) {
    this.value = initialValue;
    this.range = range;
  }

  public final void addIntValueChangeListener(IIntValueChangedListener changeListener) {
    intValueControl.addIntValueChangeListener(changeListener);
  }

  public Range getRange() {
    return range;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    value = range.getNearestValue(value);
    if (this.value == value) {
      return;
    }
    this.value = value;
    intValueControl.fireValueChangedEvent(value);
  }

  public Integer getMaximum() {
    return range.getUpperBound();
  }

  public Integer getMinimum() {
    return range.getLowerBound();
  }
}