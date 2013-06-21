package net.sf.anathema.hero.initialization;

import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ModelInitializationList<E extends ModelTreeEntry> implements Iterable<Identifier> {

  private List<Identifier> sortedModelIds = new ArrayList<>();
  private List<Identifier> configuredIdentifiers;
  private Iterable<E> availableEntries;

  public ModelInitializationList(List<Identifier> configuredIdentifiers, Iterable<E> availableEntries) {
    this.configuredIdentifiers = configuredIdentifiers;
    this.availableEntries = availableEntries;
    startSort();
  }

  private void startSort() {
    for (Identifier entry : configuredIdentifiers) {
      handleEntry(entry);
    }
  }

  private void handleEntry(Identifier entry) {
    if (sortedModelIds.contains(entry)) {
      return;
    }
    handleRequirements(entry);
    addModelId(entry);
  }

  private void handleRequirements(Identifier entry) {
    E entryModel = findConfigurationWithId(entry);
    for (Identifier id : entryModel.getRequiredModelIds()) {
      if (sortedModelIds.contains(id)) {
        continue;
      }
      E configuration = findConfigurationWithId(id);
      if (configuration != null) {
        handleEntry(id);
      }
      addModelId(id);
    }
  }

  private void addModelId(Identifier id) {
    if (sortedModelIds.contains(id)) {
      return;
    }
    sortedModelIds.add(id);
  }

  private E findConfigurationWithId(Identifier id) {
    for (E entry : availableEntries) {
      if (entry.getModelId().equals(id)) {
        return entry;
      }
    }
    return null;
  }

  public Identifier get(int index) {
    return sortedModelIds.get(index);
  }

  public int size() {
    return sortedModelIds.size();
  }

  @Override
  public Iterator<Identifier> iterator() {
    return sortedModelIds.iterator();
  }
}
