package net.sf.anathema.lib.control.booleanvalue;

import java.util.ArrayList;
import java.util.List;

public class BooleanValueControl {

  private final List<IBooleanValueChangedListener> listeners = new ArrayList<IBooleanValueChangedListener>();

  public synchronized void addValueChangeListener(IBooleanValueChangedListener listener) {
    this.listeners.add(listener);
  }

  public synchronized void removeValueChangeListener(IBooleanValueChangedListener listener) {
    this.listeners.remove(listener);
  }

  public final synchronized void fireValueChangedEvent(boolean value) {
    List<IBooleanValueChangedListener> cloneList = new ArrayList<IBooleanValueChangedListener>(listeners);
    for (IBooleanValueChangedListener listener : cloneList) {
      listener.valueChanged(value);
    }
  }
}