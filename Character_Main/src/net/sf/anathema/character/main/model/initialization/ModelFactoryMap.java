package net.sf.anathema.character.main.model.initialization;

import net.sf.anathema.character.main.model.HeroModelFactory;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ModelFactoryMap {
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

  public void assertContainsConfiguredModel(String configuredId) {
    String errorMessage = "No model factory found for configured model id.";
    assertContainsModel(configuredId, errorMessage);
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
}