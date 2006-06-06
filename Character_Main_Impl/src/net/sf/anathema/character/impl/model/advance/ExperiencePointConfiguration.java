package net.sf.anathema.character.impl.model.advance;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.sf.anathema.character.model.advance.IExperiencePointConfiguration;
import net.sf.anathema.character.model.advance.IExperiencePointConfigurationListener;
import net.sf.anathema.character.model.advance.IExperiencePointEntry;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;

public class ExperiencePointConfiguration implements IExperiencePointConfiguration {

  private final List<IExperiencePointEntry> entries = new ArrayList<IExperiencePointEntry>();
  private final GenericControl<IExperiencePointConfigurationListener> control = new GenericControl<IExperiencePointConfigurationListener>();
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

  private void fireEntryRemovedEvent(final IExperiencePointEntry entry) {
    control.forAllDo(new IClosure<IExperiencePointConfigurationListener>() {
      public void execute(IExperiencePointConfigurationListener input) {
        input.entryRemoved(entry);
      }
    });
  }

  private void fireEntryAddedEvent(final IExperiencePointEntry entry) {
    control.forAllDo(new IClosure<IExperiencePointConfigurationListener>() {
      public void execute(IExperiencePointConfigurationListener input) {
        input.entryAdded(entry);
      }
    });
  }

  private void fireEntryChangedEvent(final IExperiencePointEntry entry) {
    control.forAllDo(new IClosure<IExperiencePointConfigurationListener>() {
      public void execute(IExperiencePointConfigurationListener input) {
        input.entryChanged(entry);
      }
    });
  }

  public void addExperiencePointConfigurationListener(IExperiencePointConfigurationListener listener) {
    control.addListener(listener);
  }

  public int getTotalExperiencePoints() {
    int sum = 0;
    for (IExperiencePointEntry entry : getAllEntries()) {
      if (entry.getExperiencePoints() > 0) {
        sum += entry.getExperiencePoints();
      }
    }
    return sum;
  }

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