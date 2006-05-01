package net.sf.anathema.lib.workflow.booleanvalue;

import net.sf.anathema.lib.control.booleanvalue.BooleanValueControl;
import net.sf.anathema.lib.control.booleanvalue.IBooleanValueChangedListener;

public class BooleanValueModel {

  private final BooleanValueControl control = new BooleanValueControl();
  private boolean value;

  public BooleanValueModel(boolean value) {
    this.value = value;
  }

  public boolean getValue() {
    return value;
  }

  public void addChangeListener(IBooleanValueChangedListener listener) {
    control.addValueChangeListener(listener);
  }

  public void setValue(boolean value) {
    if (value == this.value) {
      return;
    }
    this.value = value;
    control.fireValueChangedEvent(value);
  }
}