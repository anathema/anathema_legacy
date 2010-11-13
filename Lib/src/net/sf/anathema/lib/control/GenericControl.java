package net.sf.anathema.lib.control;

import java.util.ArrayList;
import java.util.List;

public class GenericControl<L> {

  private final List<L> listeners = new ArrayList<L>();

  public synchronized void addListener(L listener) {
    this.listeners.add(listener);
  }

  public synchronized void removeListener(L listener) {
    this.listeners.remove(listener);
  }

  public final void forAllDo(IClosure<L> block) {
    List<L> cloneList = cloneListenerList();
    for (L listener : cloneList) {
      block.execute(listener);
    }
  }

  private synchronized List<L> cloneListenerList() {
    return new ArrayList<L>(listeners);
  }
}