package net.sf.anathema.character.main.model.initialization;

import net.sf.anathema.character.change.ChangeAnnouncer;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.main.model.CharacterModel;
import net.sf.anathema.character.main.model.CharacterModelAutoCollector;
import net.sf.anathema.character.main.model.CharacterModelFactory;
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
  private ICharacterTemplate template;

  public CharacterModelInitializer(InitializationContext context, ICharacterTemplate template) {
    this.context = context;
    this.template = template;
  }

  public void addModels(ICharacterGenerics generics, DefaultHero hero) {
    ModelFactoryAutoCollector collector = new ModelFactoryAutoCollector(generics);
    ModelFactoryMap factoryMap = new ModelFactoryMap(collector);
    Iterable<Identifier> sortedRelevantModelIds = getSortedModelIdsForCharacter(factoryMap);
    Iterable<CharacterModel> sortedModels = createSortedModels(generics, factoryMap, sortedRelevantModelIds);
    initializeModelsInOrder(hero, sortedModels);
  }

  private Iterable<Identifier> getSortedModelIdsForCharacter(ModelFactoryMap factoryMap) {
    List<CharacterModelFactory> configuredFactories = collectConfiguredModelFactories(factoryMap);
    return new ModelInitializationList<>(configuredFactories);
  }

  private Iterable<CharacterModel> createSortedModels(ICharacterGenerics generics, ModelFactoryMap factoryMap, Iterable<Identifier> sortedRelevantModelIds) {
    TemplateFactory templateFactory = new DefaultTemplateFactory(generics);
    List<CharacterModel> modelList = new ArrayList<>();
    for (Identifier modelId : sortedRelevantModelIds) {
      factoryMap.assertContainsRequiredModel(modelId.getId());
      CharacterModelFactory factory = factoryMap.get(modelId.getId());
      modelList.add(factory.create(templateFactory));
    }
    return modelList;
  }

  private void initializeModelsInOrder(DefaultHero hero, Iterable<CharacterModel> modelList) {
    for (CharacterModel model : modelList) {
      model.initialize(context, hero);
      hero.addModel(model);
    }
  }

  private List<CharacterModelFactory> collectConfiguredModelFactories(ModelFactoryMap factoryMap) {
    List<CharacterModelFactory> configuredFactories = new ArrayList<>();
    for (String configuredId : template.getModels()) {
      factoryMap.assertContainsConfiguredModel(configuredId);
      CharacterModelFactory factory = factoryMap.get(configuredId);
      configuredFactories.add(factory);
    }
    return configuredFactories;
  }
}