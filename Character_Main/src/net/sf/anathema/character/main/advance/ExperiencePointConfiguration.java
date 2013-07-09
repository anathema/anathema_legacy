package net.sf.anathema.character.main.advance;

import net.sf.anathema.lib.control.ObjectValueListener;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.List;

public class ExperiencePointConfiguration implements IExperiencePointConfiguration {

  public static final IExperiencePointEntry NO_ENTRY = null;
  private IExperiencePointEntry currentlySelectedEntry = NO_ENTRY;
  private final List<IExperiencePointEntry> entries = new ArrayList<>();
  private final Announcer<ExperiencePointConfigurationListener> control = Announcer.to(ExperiencePointConfigurationListener.class);
  private final ObjectValueListener<IExperiencePointEntry> entryChangeListener = new ObjectValueListener<IExperiencePointEntry>() {
    @Override
    public void valueChanged(IExperiencePointEntry entry) {
      fireEntryChangedEvent();
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
    fireEntryAddedEvent();
    newEntry.addChangeListener(entryChangeListener);
  }

  private IExperiencePointEntry addEntryWithoutEvent() {
    IExperiencePointEntry newEntry = new ExperiencePointEntry();
    entries.add(newEntry);
    return newEntry;
  }

  @Override
  public void removeEntry() {
    currentlySelectedEntry.removeChangeListener(entryChangeListener);
    entries.remove(currentlySelectedEntry);
    fireEntryRemovedEvent();
    this.currentlySelectedEntry = NO_ENTRY;
  }

  private void fireEntryRemovedEvent() {
    control.announce().entryRemoved();
  }

  private void fireEntryAddedEvent() {
    control.announce().entryAdded();
  }

  private void fireEntryChangedEvent() {
    control.announce().entryChanged();
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

  @Override
  public void selectForChange(IExperiencePointEntry entry) {
    this.currentlySelectedEntry = entry;
    control.announce().selectionChanged(entry);
  }

  @Override
  public void updateCurrentSelection(String description, int points) {
    currentlySelectedEntry.getTextualDescription().setText(description);
    currentlySelectedEntry.setExperiencePoints(points);
  }
}