package net.sf.anathema.character.impl.model.advance;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.sf.anathema.character.model.advance.IExperiencePointConfiguration;
import net.sf.anathema.character.model.advance.IExperiencePointConfigurationListener;
import net.sf.anathema.character.model.advance.IExperiencePointEntry;

public class ExperiencePointConfiguration implements IExperiencePointConfiguration {

  private final List<IExperiencePointEntry> entries = new ArrayList<IExperiencePointEntry>();
  private final List<IExperiencePointConfigurationListener> listeners = new ArrayList<IExperiencePointConfigurationListener>();
  private final ChangeListener entryChangeListener = new ChangeListener() {
    public void stateChanged(ChangeEvent e) {
      fireEntryChangedEvent((IExperiencePointEntry) e.getSource());
    }
  };

  public IExperiencePointEntry[] getAllEntries() {
    return entries.toArray(new IExperiencePointEntry[entries.size()]);
  }

  public IExperiencePointEntry addEntry() {
    IExperiencePointEntry newEntry = new ExperiencePointEntry();
    entries.add(newEntry);
    fireEntryAddedEvent(newEntry);
    newEntry.addChangeListener(entryChangeListener);
    return newEntry;
  }

  public void removeEntry(IExperiencePointEntry entry) {
    entries.remove(entry);
    entry.removeChangeListener(entryChangeListener);
    fireEntryRemovedEvent(entry);
  }

  private void fireEntryRemovedEvent(IExperiencePointEntry entry) {
    for (IExperiencePointConfigurationListener listener : cloneListenerList()) {
      listener.entryRemoved(entry);
    }
  }

  private void fireEntryAddedEvent(IExperiencePointEntry entry) {
    for (IExperiencePointConfigurationListener listener : cloneListenerList()) {
      listener.entryAdded(entry);
    }
  }

  private void fireEntryChangedEvent(IExperiencePointEntry entry) {
    for (IExperiencePointConfigurationListener listener : cloneListenerList()) {
      listener.entryChanged(entry);
    }
  }
  
  private synchronized List<IExperiencePointConfigurationListener> cloneListenerList() {
    return new ArrayList<IExperiencePointConfigurationListener>(listeners);
  }

  public synchronized void addExperiencePointConfigurationListener(IExperiencePointConfigurationListener listener) {
    listeners.add(listener);
  }

  
  public int getTotalExperiencePoints() {
    int sum = 0;
    for(IExperiencePointEntry entry : getAllEntries()) {
      sum += entry.getExperiencePoints();
    }
    return sum;
  }
}