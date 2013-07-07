package net.sf.anathema.character.main.library.removableentry.presenter;

import java.util.List;

public interface IRemovableEntryModel<E> {

  E commitSelection();

  List<E> getEntries();

  void removeEntry(E entry);

  void addModelChangeListener(IRemovableEntryListener<E> listener);
}