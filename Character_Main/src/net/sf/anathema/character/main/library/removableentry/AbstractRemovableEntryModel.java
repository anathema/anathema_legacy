package net.sf.anathema.character.main.library.removableentry;

import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractRemovableEntryModel<E> implements IRemovableEntryModel<E> {

  private final List<E> entries = new ArrayList<>();
  private final Announcer<IRemovableEntryListener> control = Announcer.to(IRemovableEntryListener.class);

  @SuppressWarnings("unchecked")
  @Override
  public E commitSelection() {
    E entry = createEntry();
    entries.add(entry);
    control.announce().entryAdded(entry);
    return entry;
  }

  protected abstract E createEntry();

  @SuppressWarnings("unchecked")
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