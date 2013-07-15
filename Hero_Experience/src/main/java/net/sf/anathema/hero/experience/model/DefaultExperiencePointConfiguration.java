package net.sf.anathema.hero.experience.model;

import net.sf.anathema.hero.advance.experience.ExperiencePointConfiguration;
import net.sf.anathema.hero.advance.experience.ExperiencePointConfigurationListener;
import net.sf.anathema.hero.advance.experience.ExperiencePointEntry;
import net.sf.anathema.hero.advance.experience.ExperienceSelectionListener;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.List;

public class DefaultExperiencePointConfiguration implements ExperiencePointConfiguration {

  public static final ExperiencePointEntry NO_ENTRY = null;
  private ExperiencePointEntry currentlySelectedEntry = NO_ENTRY;
  private final List<ExperiencePointEntry> entries = new ArrayList<>();
  private final Announcer<ExperiencePointConfigurationListener> control = Announcer.to(ExperiencePointConfigurationListener.class);
  private final Announcer<ExperienceSelectionListener> selectionAnnouncer = Announcer.to(ExperienceSelectionListener.class);

  @Override
  public ExperiencePointEntry[] getAllEntries() {
    return entries.toArray(new ExperiencePointEntry[entries.size()]);
  }

  @Override
  public ExperiencePointEntry addEntry() {
    ExperiencePointEntry newEntry = addEntryWithoutEvent();
    fireChangeEvent();
    return newEntry;
  }

  private ExperiencePointEntry addEntryWithoutEvent() {
    ExperiencePointEntry newEntry = new DefaultExperiencePointEntry();
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
    for (ExperiencePointEntry entry : getAllEntries()) {
      if (entry.getExperiencePoints() > 0) {
        sum += entry.getExperiencePoints();
      }
    }
    return sum;
  }

  @Override
  public int getExtraSpendings() {
    int sum = 0;
    for (ExperiencePointEntry entry : getAllEntries()) {
      if (entry.getExperiencePoints() < 0) {
        sum -= entry.getExperiencePoints();
      }
    }
    return sum;
  }

  @Override
  public void selectForChange(ExperiencePointEntry entry) {
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
  public ExperiencePointEntry getCurrentSelection() {
    return currentlySelectedEntry;
  }
}