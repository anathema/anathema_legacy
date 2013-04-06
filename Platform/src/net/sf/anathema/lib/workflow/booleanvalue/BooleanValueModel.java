package net.sf.anathema.lib.workflow.booleanvalue;

import net.sf.anathema.lib.control.IBooleanValueChangedListener;
import org.jmock.example.announcer.Announcer;

public class BooleanValueModel {

  private final Announcer<IBooleanValueChangedListener> control = Announcer.to(IBooleanValueChangedListener.class);
  private boolean value;

  public BooleanValueModel(boolean value) {
    this.value = value;
  }

  public boolean getValue() {
    return value;
  }

  public void addChangeListener(IBooleanValueChangedListener listener) {
    control.addListener(listener);
  }

  public void setValue(boolean value) {
    if (value == this.value) {
      return;
    }
    this.value = value;
    control.announce().valueChanged(value);
  }
}