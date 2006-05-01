package net.sf.anathema.lib.workflow.intvalue;

import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.control.intvalue.IntValueControl;

public class SimpleIntValueModel implements IIntValueModel {

  private final IntValueControl intValueControl = new IntValueControl();
  private int value;

  public SimpleIntValueModel(int value) {
    this.value = value;
  }

  public void setValue(int value) {
    if (this.value == value) {
      return;
    }
    this.value = value;
    intValueControl.fireValueChangedEvent(value);
  }

  public int getValue() {
    return value;
  }

  public final void addIntValueChangeListener(IIntValueChangedListener changeListener) {
    intValueControl.addIntValueChangeListener(changeListener);
  }
}