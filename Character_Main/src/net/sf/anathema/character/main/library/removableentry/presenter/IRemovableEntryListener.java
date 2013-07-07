package net.sf.anathema.character.main.library.removableentry.presenter;

public interface IRemovableEntryListener<E> {

  void entryAdded(E entry);

  void entryRemoved(E entry);

  void entryAllowed(boolean complete);
}