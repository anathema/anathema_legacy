package net.sf.anathema.character.framework.library.removableentry;

import java.util.List;

public interface RemovableEntryModel<E> {

  E commitSelection();

  List<E> getEntries();

  void removeEntry(E entry);

  void addModelChangeListener(RemovableEntryListener<E> listener);
}