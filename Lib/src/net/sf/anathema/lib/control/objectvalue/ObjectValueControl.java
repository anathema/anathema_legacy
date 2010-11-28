package net.sf.anathema.lib.control.objectvalue;

import java.util.ArrayList;
import java.util.List;

public class ObjectValueControl<T> {

  private final List<IObjectValueChangedListener<T>> listeners = new ArrayList<IObjectValueChangedListener<T>>();

  public synchronized void addObjectValueChangeListener(IObjectValueChangedListener<T> listener) {
    this.listeners.add(listener);
  }

  public synchronized void removeObjectValueChangeListener(IObjectValueChangedListener<T> listener) {
    this.listeners.remove(listener);
  }

  public final synchronized void fireValueChangedEvent(final T newValue) {
    List<IObjectValueChangedListener<T>> cloneList = new ArrayList<IObjectValueChangedListener<T>>(listeners);
    for (IObjectValueChangedListener<T> listener : cloneList) {
      listener.valueChanged(newValue);
    }
  }
}