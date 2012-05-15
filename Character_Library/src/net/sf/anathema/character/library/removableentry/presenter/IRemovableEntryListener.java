package net.sf.anathema.character.library.removableentry.presenter;

public interface IRemovableEntryListener<E> {

  void entryAdded(E entry);

  void entryRemoved(E entry);

  void entryAllowed(boolean complete);
}