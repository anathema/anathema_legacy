package net.sf.anathema.character.library.removableentry.presenter;

import java.util.List;

public interface IRemovableEntryModel<E> {

  public void commitSelection();

  public List<E> getEntries();

  public void removeEntry(E entry);

  public void addModelChangeListener(IRemovableEntryListener<E> listener);
}