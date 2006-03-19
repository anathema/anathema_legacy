package net.sf.anathema.lib.control.intvalue;

import java.util.ArrayList;
import java.util.List;

public class CheckedIntValueControl {

  private final List<ICheckedIntValueChangedListener> listeners = new ArrayList<ICheckedIntValueChangedListener>();

  public synchronized void addIntValueChangeListener(ICheckedIntValueChangedListener listener) {
    this.listeners.add(listener);
  }

  public synchronized void removeIntValueChangeListener(ICheckedIntValueChangedListener listener) {
    this.listeners.remove(listener);
  }

  public final synchronized void fireValueChangedEvent(CheckedValue value) {
    for (ICheckedIntValueChangedListener listener : listeners) {
      listener.valueChanged(value);
    }
  }
}