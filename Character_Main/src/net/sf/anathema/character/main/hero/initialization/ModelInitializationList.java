package net.sf.anathema.character.main.hero.initialization;

import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ModelInitializationList<E extends ModelTreeEntry> implements Iterable<Identifier> {

  private List<Identifier> sortedModelIds = new ArrayList<>();
  private List<E> configuredEntries;

  public ModelInitializationList(List<E> configuredEntries) {
    this.configuredEntries = configuredEntries;
    startSort(configuredEntries);
  }

  private void startSort(List<E> configuredEntries) {
    for (E entry : configuredEntries) {
      handleEntry(entry);
    }
  }

  private void handleEntry(E entry) {
    if (sortedModelIds.contains(entry.getModelId())) {
      return;
    }
    handleRequirements(entry);
    addModelId(entry.getModelId());
  }

  private void handleRequirements(E entry) {
    for (Identifier id : entry.getRequiredModelIds()) {
      if (sortedModelIds.contains(id)) {
        continue;
      }
      E configuration = findConfigurationWithId(id);
      if (configuration != null) {
        handleEntry(configuration);
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
    for (E entry : configuredEntries) {
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
