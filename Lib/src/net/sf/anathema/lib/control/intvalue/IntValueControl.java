package net.sf.anathema.lib.control.intvalue;

import java.util.ArrayList;
import java.util.List;

public class IntValueControl {

  private final List<IIntValueChangedListener> listeners = new ArrayList<IIntValueChangedListener>();

  public synchronized void addIntValueChangeListener(IIntValueChangedListener listener) {
    this.listeners.add(listener);
  }

  public synchronized void removeIntValueChangeListener(IIntValueChangedListener listener) {
    this.listeners.remove(listener);
  }

  public final synchronized void fireValueChangedEvent(final int value) {
    List<IIntValueChangedListener> cloneList = new ArrayList<IIntValueChangedListener>(listeners);
    for (IIntValueChangedListener listener : cloneList) {
      listener.valueChanged(value);
    }
  }
}