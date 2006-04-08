package net.sf.anathema.character.library.removableentry.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryListener;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryModel;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;

public abstract class AbstractRemovableEntryModel<E> implements IRemovableEntryModel<E> {

  private final List<E> entries = new ArrayList<E>();
  private final GenericControl<IRemovableEntryListener<E>> control = new GenericControl<IRemovableEntryListener<E>>();

  public E commitSelection() {
    final E entry = createEntry();
    entries.add(entry);
    control.forAllDo(new IClosure<IRemovableEntryListener<E>>() {
      public void execute(IRemovableEntryListener<E> input) {
        input.entryAdded(entry);
      }
    });
    return entry;
  }

  protected abstract E createEntry();

  public void removeEntry(final E entry) {
    entries.remove(entry);
    control.forAllDo(new IClosure<IRemovableEntryListener<E>>() {
      public void execute(IRemovableEntryListener<E> input) {
        input.entryRemoved(entry);
      }
    });
  }

  public List<E> getEntries() {
    return Collections.unmodifiableList(entries);
  }

  protected void fireEntryChanged() {
    control.forAllDo(new IClosure<IRemovableEntryListener<E>>() {
      public void execute(IRemovableEntryListener<E> input) {
        input.entryAllowed(isEntryAllowed());
      }
    });
  }

  protected abstract boolean isEntryAllowed();

  public void addModelChangeListener(IRemovableEntryListener<E> listener) {
    control.addListener(listener);
  }
}