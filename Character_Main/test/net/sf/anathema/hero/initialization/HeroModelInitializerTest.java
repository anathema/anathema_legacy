package net.sf.anathema.hero.initialization;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.template.HeroTemplate;
import net.sf.anathema.hero.model.DefaultHero;
import net.sf.anathema.hero.model.HeroModelAutoCollector;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.model.InitializationContext;
import net.sf.anathema.initialization.ObjectFactory;
import net.sf.anathema.lib.util.SimpleIdentifier;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HeroModelInitializerTest {

  private static final String Configured_Model = "Configured";
  private static final String Required_Model = "RequirementOfConfiguredModel";
  private static final String Transitively_Required_Model = "TransitiveRequiremmentOfRequiredModel";

  private final List<String> configuredModels = new ArrayList<>();
  private final List availableModels = new ArrayList<>();
  private final InitializationContext context = mock(InitializationContext.class);
  private final HeroTemplate template = createTemplate();
  private final HeroModelInitializer initializer = new HeroModelInitializer(context, template);

  @Test
  public void instantiatesModelsThatAreRequiredThroughTransitiveDependencies() throws Exception {
    addModelToConfiguration(Configured_Model);
    setupAvailableModelsInAChainOfThree();
    DefaultHero hero = createHero();
    initializeModelsForHero(hero);
    assertThat(hero.getModel(new SimpleIdentifier(Transitively_Required_Model)), is(not(nullValue())));
  }

  private DefaultHero createHero() {
    return new DefaultHero(template);
  }

  private void initializeModelsForHero(DefaultHero hero) {
    ICharacterGenerics generics = createGenerics();
    initializer.addModels(generics, hero);
  }

  @SuppressWarnings("unchecked")
  private void setupAvailableModelsInAChainOfThree() {
    HeroModelFactory configuredModelFactory = createModelFactory(Configured_Model, Required_Model);
    HeroModelFactory requiredModelFactory = createModelFactory(Required_Model, Transitively_Required_Model);
    HeroModelFactory transitiveModelFactory = createModelFactory(Transitively_Required_Model);
    availableModels.add(configuredModelFactory);
    availableModels.add(requiredModelFactory);
    availableModels.add(transitiveModelFactory);
  }

  private void addModelToConfiguration(String id) {
    configuredModels.add(id);
  }

  @SuppressWarnings("unchecked")
  private ICharacterGenerics createGenerics() {
    ICharacterGenerics generics = mock(ICharacterGenerics.class);
    ObjectFactory objectFactory = mock(ObjectFactory.class);
    when(objectFactory.instantiateAll(HeroModelAutoCollector.class)).thenReturn(availableModels);
    when(generics.getInstantiater()).thenReturn(objectFactory);
    return generics;
  }

  private DummyModelFactory createModelFactory(String idOfModel, String idOfRequirement) {
    DummyModelFactory factory = createModelFactory(idOfModel);
    factory.addRequirement(new SimpleIdentifier(idOfRequirement));
    return factory;
  }

  private DummyModelFactory createModelFactory(String idOfModel) {
    return new DummyModelFactory(new SimpleIdentifier(idOfModel));
  }

  private HeroTemplate createTemplate() {
    HeroTemplate template = mock(HeroTemplate.class);
    when(template.getModels()).thenReturn(configuredModels);
    return template;
  }
}