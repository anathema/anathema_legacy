package net.sf.anathema.hero.initialization;

import net.sf.anathema.character.main.framework.ICharacterGenerics;
import net.sf.anathema.character.main.template.ConfiguredModel;
import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.hero.model.DefaultHero;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.model.InitializationContext;
import net.sf.anathema.hero.template.DefaultTemplateFactory;
import net.sf.anathema.hero.template.TemplateFactory;

import java.util.ArrayList;
import java.util.List;

public class HeroModelInitializer {

  private InitializationContext context;
  private HeroTemplate template;

  public HeroModelInitializer(InitializationContext context, HeroTemplate template) {
    this.context = context;
    this.template = template;
  }

  public void addModels(ICharacterGenerics generics, DefaultHero hero) {
    ModelFactoryAutoCollector collector = new ModelFactoryAutoCollector(generics);
    ModelFactoryMap factoryMap = new ModelFactoryMap(collector);
    Iterable<ConfiguredModel> sortedRelevantModelIds = getSortedModelIdsForHero(factoryMap);
    Iterable<HeroModel> sortedModels = createSortedModels(generics, factoryMap, sortedRelevantModelIds);
    initializeModelsInOrder(hero, sortedModels);
  }

  private Iterable<ConfiguredModel> getSortedModelIdsForHero(ModelFactoryMap factoryMap) {
    return new ModelInitializationList<ModelTreeEntry>(template.getModels(), factoryMap);
  }

  private Iterable<HeroModel> createSortedModels(ICharacterGenerics generics, ModelFactoryMap factoryMap, Iterable<ConfiguredModel> sortedRelevantModelIds) {
    TemplateFactory templateFactory = new DefaultTemplateFactory(generics);
    List<HeroModel> modelList = new ArrayList<>();
    for (ConfiguredModel configuredModel : sortedRelevantModelIds) {
      factoryMap.assertContainsRequiredModel(configuredModel.modelId.getId());
      HeroModelFactory factory = factoryMap.get(configuredModel.modelId.getId());
      modelList.add(factory.create(templateFactory, configuredModel.modelTemplateId));
    }
    return modelList;
  }

  private void initializeModelsInOrder(DefaultHero hero, Iterable<HeroModel> modelList) {
    for (HeroModel model : modelList) {
      model.initialize(context, hero);
      model.initializeListening(hero.getChangeAnnouncer());
      hero.addModel(model);
    }
  }
}