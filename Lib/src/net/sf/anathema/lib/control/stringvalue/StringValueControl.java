package net.sf.anathema.lib.control.stringvalue;

import java.util.ArrayList;
import java.util.List;

public class StringValueControl {

  private final List<IStringValueChangedListener> listeners = new ArrayList<IStringValueChangedListener>();

  public synchronized void addStringValueChangeListener(IStringValueChangedListener listener) {
    this.listeners.add(listener);
  }

  public synchronized void removeStringValueChangeListener(IStringValueChangedListener listener) {
    this.listeners.remove(listener);
  }

  public final synchronized void fireValueChangedEvent(final String value) {
    List<IStringValueChangedListener> cloneList = new ArrayList<IStringValueChangedListener>(listeners);
    for (IStringValueChangedListener listener : cloneList) {
      listener.valueChanged(value);
    }
  }
}