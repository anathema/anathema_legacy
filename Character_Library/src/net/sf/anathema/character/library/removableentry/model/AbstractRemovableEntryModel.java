package net.sf.anathema.character.library.removableentry.model;

import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryListener;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryModel;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractRemovableEntryModel<E> implements IRemovableEntryModel<E> {

  private final List<E> entries = new ArrayList<>();
  private final Announcer<IRemovableEntryListener> control = Announcer.to(IRemovableEntryListener.class);

  @Override
  public E commitSelection() {
    E entry = createEntry();
    entries.add(entry);
    control.announce().entryAdded(entry);
    return entry;
  }

  protected abstract E createEntry();

  @Override
  public void removeEntry(E entry) {
    entries.remove(entry);
    control.announce().entryRemoved(entry);
  }

  @Override
  public List<E> getEntries() {
    return Collections.unmodifiableList(entries);
  }

  protected void fireEntryChanged() {
    control.announce().entryAllowed(isEntryAllowed());
  }

  protected abstract boolean isEntryAllowed();

  @Override
  public void addModelChangeListener(IRemovableEntryListener<E> listener) {
    control.addListener(listener);
  }
}