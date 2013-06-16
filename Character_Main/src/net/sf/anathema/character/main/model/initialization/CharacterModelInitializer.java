package net.sf.anathema.character.main.model.initialization;

import net.sf.anathema.character.change.ChangeAnnouncer;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.main.model.DefaultHero;
import net.sf.anathema.character.main.model.DefaultTemplateFactory;
import net.sf.anathema.character.main.model.CharacterModel;
import net.sf.anathema.character.main.model.CharacterModelAutoCollector;
import net.sf.anathema.character.main.model.CharacterModelFactory;
import net.sf.anathema.character.main.modeltemplate.TemplateFactory;
import net.sf.anathema.initialization.ObjectFactory;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharacterModelInitializer {

  private ChangeAnnouncer changeAnnouncer;
  private ICharacterTemplate template;

  public CharacterModelInitializer(ChangeAnnouncer changeAnnouncer, ICharacterTemplate template) {
    this.changeAnnouncer = changeAnnouncer;
    this.template = template;
  }

  public void addModels(ICharacterGenerics generics, DefaultHero hero) {
    Map<String, CharacterModelFactory> factoriesById = createFactoriesMap(generics);
    TemplateFactory templateFactory = new DefaultTemplateFactory(generics);
    Iterable<Identifier> sortedListOfRelevantModelIds = getSortedModelIdsForCharacter(factoriesById);
    List<CharacterModel> modelList = new ArrayList<>();
    for (Identifier modelId : sortedListOfRelevantModelIds) {
      if (!factoriesById.containsKey(modelId.getId())) {
        throw new IllegalStateException("No model factory found for dependent model id " + modelId);
      }
      CharacterModelFactory factory = factoriesById.get(modelId.getId());
      modelList.add(factory.create(templateFactory));
    }
    for (CharacterModel model : modelList) {
      model.initialize(changeAnnouncer, hero);
      hero.addModel(model);
    }
  }

  private Map<String, CharacterModelFactory> createFactoriesMap(ICharacterGenerics generics) {
    Map<String, CharacterModelFactory> factoriesById = new HashMap<>();
    for (CharacterModelFactory factory : collectModelFactories(generics)) {
      factoriesById.put(factory.getModelId().getId(), factory);
    }
    return factoriesById;
  }

  private Iterable<Identifier> getSortedModelIdsForCharacter(Map<String, CharacterModelFactory> factoriesById) {
    List<CharacterModelFactory> configuredFactories = collectConfiguredModelFactories(factoriesById);
    return new ModelInitializationList<>(configuredFactories);
  }

  private List<CharacterModelFactory> collectConfiguredModelFactories(Map<String, CharacterModelFactory> factoriesById) {
    List<CharacterModelFactory> configuredFactories = new ArrayList<>();
    for (String configuredId : template.getModels()) {
      if (!factoriesById.containsKey(configuredId)) {
        throw new IllegalStateException("No model factory found for configured model id " + configuredId);
      }
      configuredFactories.add(factoriesById.get(configuredId));
    }
    return configuredFactories;
  }

  private Collection<CharacterModelFactory> collectModelFactories(ICharacterGenerics generics) {
    ObjectFactory objectFactory = generics.getInstantiater();
    return objectFactory.instantiateAll(CharacterModelAutoCollector.class);
  }
}
