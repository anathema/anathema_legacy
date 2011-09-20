package net.sf.anathema.character.impl.model;

import net.sf.anathema.character.model.IIntegerDescription;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public class IntegerDescription implements IIntegerDescription {

  private final ChangeControl control = new ChangeControl();
  private int value;

  public IntegerDescription(int value) {
    this.value = value;
  }

  public final int getValue() {
    return value;
  }

  public final void setValue(int value) {
    if (this.value == value) {
      return;
    }
    this.value = value;
    fireValueChangedEvent();
  }

  public final void addChangeListener(IChangeListener listener) {
    control.addChangeListener(listener);
  }

  private void fireValueChangedEvent() {
    control.fireChangedEvent();
  }
}