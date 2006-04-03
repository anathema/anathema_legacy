package net.sf.anathema.character.library.removableentry.presenter;

public interface IRemovableEntryListener<E> {

  public void entryAdded(E entry);

  public void entryRemoved(E entry);

  public void entryComplete(boolean complete);
}