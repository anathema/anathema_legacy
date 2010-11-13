package net.sf.anathema.character.library.removableentry.presenter;

import net.sf.anathema.lib.control.change.IChangeListener;

public final class RemovableEntryChangeAdapter<T> implements IRemovableEntryListener<T> {
  private final IChangeListener listener;

  public RemovableEntryChangeAdapter(IChangeListener listener) {
    this.listener = listener;
  }

  public void entryAdded(T entry) {
    listener.changeOccured();
  }

  public void entryAllowed(boolean complete) {
    // nothing to do
  }

  public void entryRemoved(T entry) {
    listener.changeOccured();
  }
}