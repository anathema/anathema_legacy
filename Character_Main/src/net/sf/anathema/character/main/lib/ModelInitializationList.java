package net.sf.anathema.character.main.lib;

import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class ModelInitializationList<E extends ModelTreeEntry> {

  private List<Identifier> sortedEntries = new ArrayList<>();
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
    for (Identifier identifier : entry.getRequiredModelIds()) {
      handleRequirement(identifier);
    }
    handleSafeEntry(entry);
  }

  private void handleRequirement(Identifier identifier) {
    if (sortedEntries.contains(identifier)) {
      return;
    }
    sortedEntries.add(identifier);
  }

  private void handleSafeEntry(E entry) {
    if (sortedEntries.contains(entry.getModelId())) {
      return;
    }
    sortedEntries.add(entry.getModelId());
  }

  public Identifier get(int index) {
    return sortedEntries.get(index);
  }

  public int size() {
    return sortedEntries.size();
  }
}
