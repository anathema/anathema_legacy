package net.sf.anathema.character.model.advance;

import net.sf.anathema.lib.control.ObjectValueListener;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.List;

public class ExperiencePointConfiguration implements IExperiencePointConfiguration {

  private final List<IExperiencePointEntry> entries = new ArrayList<>();
  private final Announcer<ExperiencePointConfigurationListener> control = Announcer.to(ExperiencePointConfigurationListener.class);
  private final ObjectValueListener<IExperiencePointEntry> entryChangeListener = new ObjectValueListener<IExperiencePointEntry>() {
    @Override
    public void valueChanged(IExperiencePointEntry entry) {
      fireEntryChangedEvent(entry);
    }
  };

  @Override
  public IExperiencePointEntry[] getAllEntries() {
    return entries.toArray(new IExperiencePointEntry[entries.size()]);
  }

  @Override
  public IExperiencePointEntry addEntry() {
    IExperiencePointEntry newEntry = addEntryWithoutEvent();
    addEntryListeningAndFireEvent(newEntry);
    return newEntry;
  }

  private void addEntryListeningAndFireEvent(IExperiencePointEntry newEntry) {
    fireEntryAddedEvent(newEntry);
    newEntry.addChangeListener(entryChangeListener);
  }

  private IExperiencePointEntry addEntryWithoutEvent() {
    IExperiencePointEntry newEntry = new ExperiencePointEntry();
    entries.add(newEntry);
    return newEntry;
  }

  @Override
  public void removeEntry(IExperiencePointEntry entry) {
    entries.remove(entry);
    entry.removeChangeListener(entryChangeListener);
    fireEntryRemovedEvent(entry);
  }

  private void fireEntryRemovedEvent(IExperiencePointEntry entry) {
    control.announce().entryRemoved(entry);
  }

  private void fireEntryAddedEvent(IExperiencePointEntry entry) {
    control.announce().entryAdded(entry);
  }

  private void fireEntryChangedEvent(IExperiencePointEntry entry) {
    control.announce().entryChanged(entry);
  }

  @Override
  public void addExperiencePointConfigurationListener(ExperiencePointConfigurationListener listener) {
    control.addListener(listener);
  }

  @Override
  public int getTotalExperiencePoints() {
    int sum = 0;
    for (IExperiencePointEntry entry : getAllEntries()) {
      if (entry.getExperiencePoints() > 0) {
        sum += entry.getExperiencePoints();
      }
    }
    return sum;
  }

  @Override
  public int getExtraSpendings() {
    int sum = 0;
    for (IExperiencePointEntry entry : getAllEntries()) {
      if (entry.getExperiencePoints() < 0) {
        sum -= entry.getExperiencePoints();
      }
    }
    return sum;
  }

}