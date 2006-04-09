package net.sf.anathema.lib.control;

import java.util.ArrayList;
import java.util.List;

public class ChangeControl {

  private final List<IChangeListener> listeners = new ArrayList<IChangeListener>();

  private synchronized List<IChangeListener> cloneListeners() {
    return new ArrayList<IChangeListener>(listeners);
  }

  public void fireChangedEvent() {
    for (IChangeListener listener : cloneListeners()) {
      listener.changeOccured();
    }
  }

  public void addChangeListener(IChangeListener listener) {
    listeners.add(listener);
  }

  public void removeChangeListener(IChangeListener listener) {
    listeners.remove(listener);
  }
}