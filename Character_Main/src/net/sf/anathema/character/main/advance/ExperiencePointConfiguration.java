package net.sf.anathema.character.main.advance;

import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.List;

public class ExperiencePointConfiguration implements IExperiencePointConfiguration {

  public static final IExperiencePointEntry NO_ENTRY = null;
  private IExperiencePointEntry currentlySelectedEntry = NO_ENTRY;
  private final List<IExperiencePointEntry> entries = new ArrayList<>();
  private final Announcer<ExperiencePointConfigurationListener> control = Announcer.to(ExperiencePointConfigurationListener.class);
  private final Announcer<ExperienceSelectionListener> selectionAnnouncer = Announcer.to(ExperienceSelectionListener.class);

  @Override
  public IExperiencePointEntry[] getAllEntries() {
    return entries.toArray(new IExperiencePointEntry[entries.size()]);
  }

  @Override
  public IExperiencePointEntry addEntry() {
    IExperiencePointEntry newEntry = addEntryWithoutEvent();
    fireChangeEvent();
    return newEntry;
  }

  private IExperiencePointEntry addEntryWithoutEvent() {
    IExperiencePointEntry newEntry = new ExperiencePointEntry();
    entries.add(newEntry);
    return newEntry;
  }

  @Override
  public void removeEntry() {
    entries.remove(currentlySelectedEntry);
    fireChangeEvent();
    selectForChange(NO_ENTRY);
  }

  private void fireChangeEvent() {
    control.announce().entriesAddedRemovedOrChanged();
  }

  @Override
  public void addExperiencePointConfigurationListener(ExperiencePointConfigurationListener listener) {
    control.addListener(listener);
  }

  @Override
  public void addEntrySelectionListener(ExperienceSelectionListener listener) {
    selectionAnnouncer.addListener(listener);
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
    selectionAnnouncer.announce().selectionChanged(entry);
  }

  @Override
  public void updateCurrentSelection(String description, int points) {
    currentlySelectedEntry.getTextualDescription().setText(description);
    currentlySelectedEntry.setExperiencePoints(points);
    fireChangeEvent();
  }

  @Override
  public IExperiencePointEntry getCurrentSelection() {
    return currentlySelectedEntry;
  }
}