package net.sf.anathema.character.main.model.initialization;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.template.HeroTemplate;
import net.sf.anathema.character.main.model.HeroModel;
import net.sf.anathema.character.main.model.CharacterModelAutoCollector;
import net.sf.anathema.character.main.model.HeroModelFactory;
import net.sf.anathema.character.main.model.DefaultHero;
import net.sf.anathema.character.main.model.DefaultTemplateFactory;
import net.sf.anathema.character.main.model.InitializationContext;
import net.sf.anathema.character.main.model.template.TemplateFactory;
import net.sf.anathema.initialization.ObjectFactory;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharacterModelInitializer {

  private InitializationContext context;
  private HeroTemplate template;

  public CharacterModelInitializer(InitializationContext context, HeroTemplate template) {
    this.context = context;
    this.template = template;
  }

  public void addModels(ICharacterGenerics generics, DefaultHero hero) {
    Map<String, HeroModelFactory> factoriesById = createFactoriesMap(generics);
    TemplateFactory templateFactory = new DefaultTemplateFactory(generics);
    Iterable<Identifier> sortedListOfRelevantModelIds = getSortedModelIdsForCharacter(factoriesById);
    List<HeroModel> modelList = new ArrayList<>();
    for (Identifier modelId : sortedListOfRelevantModelIds) {
      if (!factoriesById.containsKey(modelId.getId())) {
        throw new IllegalStateException("No model factory found for dependent model id " + modelId);
      }
      HeroModelFactory factory = factoriesById.get(modelId.getId());
      modelList.add(factory.create(templateFactory));
    }
    for (HeroModel model : modelList) {
      model.initialize(context, hero);
      hero.addModel(model);
    }
  }

  private Map<String, HeroModelFactory> createFactoriesMap(ICharacterGenerics generics) {
    Map<String, HeroModelFactory> factoriesById = new HashMap<>();
    for (HeroModelFactory factory : collectModelFactories(generics)) {
      factoriesById.put(factory.getModelId().getId(), factory);
    }
    return factoriesById;
  }

  private Iterable<Identifier> getSortedModelIdsForCharacter(Map<String, HeroModelFactory> factoriesById) {
    List<HeroModelFactory> configuredFactories = collectConfiguredModelFactories(factoriesById);
    return new ModelInitializationList<>(configuredFactories);
  }

  private List<HeroModelFactory> collectConfiguredModelFactories(Map<String, HeroModelFactory> factoriesById) {
    List<HeroModelFactory> configuredFactories = new ArrayList<>();
    for (String configuredId : template.getModels()) {
      if (!factoriesById.containsKey(configuredId)) {
        throw new IllegalStateException("No model factory found for configured model id " + configuredId);
      }
      configuredFactories.add(factoriesById.get(configuredId));
    }
    return configuredFactories;
  }

  private Collection<HeroModelFactory> collectModelFactories(ICharacterGenerics generics) {
    ObjectFactory objectFactory = generics.getInstantiater();
    return objectFactory.instantiateAll(CharacterModelAutoCollector.class);
  }
}
