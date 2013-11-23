package net.sf.anathema.hero.initialization;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import net.sf.anathema.character.main.template.ConfiguredModel;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ModelInitializationList<E extends ModelTreeEntry> implements Iterable<ConfiguredModel> {

  private List<Identifier> sortedModelIds = new ArrayList<>();
  private List<ConfiguredModel> configuredModels;
  private Iterable<E> availableEntries;
  private Logger logger = Logger.getLogger(ModelInitializationList.class);

  public ModelInitializationList(List<ConfiguredModel> configuredModels, Iterable<E> availableEntries) {
    this.configuredModels = configuredModels;
    this.availableEntries = availableEntries;
    startSort();
  }

  private void startSort() {
    for (ConfiguredModel entry : configuredModels) {
      handleEntry(entry.modelId);
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
    if (entryModel == null) {
      logger.warn("Not found entry " + entry.getId());
    }
    Iterable<Identifier> modelIds = entryModel.getRequiredModelIds();
    for (Identifier id : modelIds) {
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
  public Iterator<ConfiguredModel> iterator() {
    return Lists.transform(sortedModelIds, new Function<Identifier, ConfiguredModel>() {
      @Override
      public ConfiguredModel apply(Identifier input) {
        ConfiguredModel configuredModel = getConfiguredModelFor(input);
        if (configuredModel != null) {
          return configuredModel;
        }
        return new ConfiguredModel(input.getId(), null);
      }

      private ConfiguredModel getConfiguredModelFor(Identifier identifier) {
        for (ConfiguredModel model : configuredModels) {
          if (model.modelId.equals(identifier)) {
            return model;
          }
        }
        return null;
      }
    }).iterator();
  }
}
