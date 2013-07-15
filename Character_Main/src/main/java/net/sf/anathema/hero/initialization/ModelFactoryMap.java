package net.sf.anathema.hero.initialization;

import net.sf.anathema.hero.model.HeroModelFactory;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ModelFactoryMap implements Iterable<ModelTreeEntry> {
  private final Map<String, HeroModelFactory> factoriesById = new HashMap<>();

  public ModelFactoryMap(ModelFactoryCollector collector) {
    Collection<HeroModelFactory> factories = collector.collect();
    mapFactoriesById(factories);
  }

  public HeroModelFactory get(String configuredId) {
    return factoriesById.get(configuredId);
  }

  public void assertContainsRequiredModel(String modelId) {
    String errorMessage = "No model factory found for dependent model id.";
    assertContainsModel(modelId, errorMessage);
  }

  private void mapFactoriesById(Collection<HeroModelFactory> factories) {
    for (HeroModelFactory factory : factories) {
      factoriesById.put(factory.getModelId().getId(), factory);
    }
  }

  private void assertContainsModel(String modelId, String errorMessage) {
    if (!factoriesById.containsKey(modelId)) {
      String pattern = "{0}\nExpected model id: ''{1}''.\nKnown model ids: {2}";
      throw new IllegalStateException(MessageFormat.format(pattern, errorMessage, modelId, factoriesById.keySet()));
    }
  }

  @Override
  public Iterator<ModelTreeEntry> iterator() {
    Collection<HeroModelFactory> values = factoriesById.values();
    return new ArrayList<ModelTreeEntry>(values).iterator();
  }
}