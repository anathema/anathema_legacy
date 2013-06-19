package net.sf.anathema.hero.initialization;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.template.HeroTemplate;
import net.sf.anathema.hero.model.DefaultHero;
import net.sf.anathema.hero.template.DefaultTemplateFactory;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.model.InitializationContext;
import net.sf.anathema.hero.template.TemplateFactory;
import net.sf.anathema.lib.util.Identifier;

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
    Iterable<Identifier> sortedRelevantModelIds = getSortedModelIdsForCharacter(factoryMap);
    Iterable<HeroModel> sortedModels = createSortedModels(generics, factoryMap, sortedRelevantModelIds);
    initializeModelsInOrder(hero, sortedModels);
  }

  private Iterable<Identifier> getSortedModelIdsForCharacter(ModelFactoryMap factoryMap) {
    List<HeroModelFactory> configuredFactories = collectConfiguredModelFactories(factoryMap);
    return new ModelInitializationList<>(configuredFactories);
  }

  public HeroModelInitializer() {
    super();    //To change body of overridden methods use File | Settings | File Templates.
  }

  private Iterable<HeroModel> createSortedModels(ICharacterGenerics generics, ModelFactoryMap factoryMap, Iterable<Identifier> sortedRelevantModelIds) {
    TemplateFactory templateFactory = new DefaultTemplateFactory(generics);
    List<HeroModel> modelList = new ArrayList<>();
    for (Identifier modelId : sortedRelevantModelIds) {
      factoryMap.assertContainsRequiredModel(modelId.getId());
      HeroModelFactory factory = factoryMap.get(modelId.getId());
      modelList.add(factory.create(templateFactory));
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

  private List<HeroModelFactory> collectConfiguredModelFactories(ModelFactoryMap factoryMap) {
    List<HeroModelFactory> configuredFactories = new ArrayList<>();
    for (String configuredId : template.getModels()) {
      factoryMap.assertContainsConfiguredModel(configuredId);
      HeroModelFactory factory = factoryMap.get(configuredId);
      configuredFactories.add(factory);
    }
    return configuredFactories;
  }
}