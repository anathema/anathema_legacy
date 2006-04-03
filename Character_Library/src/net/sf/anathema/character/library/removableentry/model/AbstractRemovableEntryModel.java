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

  public void commitSelection() {
    final E entry = createEntry();
    entries.add(entry);
    control.forAllDo(new IClosure<IRemovableEntryListener<E>>() {
      public void execute(IRemovableEntryListener<E> input) {
        input.entryAdded(entry);
      }
    });
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

  protected void fireEntryComplete() {
    control.forAllDo(new IClosure<IRemovableEntryListener<E>>() {
      public void execute(IRemovableEntryListener<E> input) {
        input.entryComplete(isEntryComplete());
      }
    });
  }

  protected abstract boolean isEntryComplete();

  public void addModelChangeListener(IRemovableEntryListener<E> listener) {
    control.addListener(listener);
  }
}