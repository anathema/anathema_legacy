package net.sf.anathema.character.library.removableentry.presenter;

import net.sf.anathema.lib.control.change.IChangeListener;

public final class RemovableEntryChangeAdapter<T> implements IRemovableEntryListener<T> {
  private final IChangeListener listener;

  public RemovableEntryChangeAdapter(IChangeListener listener) {
    this.listener = listener;
  }

  @Override
  public void entryAdded(T entry) {
    listener.changeOccurred();
  }

  @Override
  public void entryAllowed(boolean complete) {
    // nothing to do
  }

  @Override
  public void entryRemoved(T entry) {
    listener.changeOccurred();
  }
}