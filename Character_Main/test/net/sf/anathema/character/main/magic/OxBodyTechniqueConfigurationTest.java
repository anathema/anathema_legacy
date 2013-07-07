package net.sf.anathema.character.main.magic;

import net.sf.anathema.character.main.caste.CasteType;
import net.sf.anathema.character.main.traits.TraitValueStrategy;
import net.sf.anathema.hero.health.HealthLevelType;
import net.sf.anathema.character.main.magic.charm.special.OxBodyTechniqueCharm;
import net.sf.anathema.character.main.traits.SimpleTraitTemplate;
import net.sf.anathema.character.main.traits.ITraitTemplate;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.character.main.library.trait.DefaultTrait;
import net.sf.anathema.character.main.library.trait.FriendlyValueChangeChecker;
import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.library.trait.favorable.FriendlyIncrementChecker;
import net.sf.anathema.character.main.library.trait.rules.FavorableTraitRules;
import net.sf.anathema.hero.health.HealthModelImpl;
import net.sf.anathema.hero.traits.TraitModel;
import net.sf.anathema.hero.traits.TraitModelFetcher;
import net.sf.anathema.character.main.testing.BasicCharacterTestCase;
import net.sf.anathema.character.main.testing.dummy.DummyCasteType;
import net.sf.anathema.character.main.testing.dummy.DummyHero;
import net.sf.anathema.character.main.testing.dummy.DummyInitializationContext;
import net.sf.anathema.character.main.charm.OxBodyCategory;
import net.sf.anathema.character.main.charm.special.IOxBodyTechniqueConfiguration;
import net.sf.anathema.character.main.charm.special.OxBodyTechniqueConfiguration;
import net.sf.anathema.character.main.traits.context.CreationTraitValueStrategy;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;

import static org.junit.Assert.assertEquals;

public class OxBodyTechniqueConfigurationTest {

  private Trait resistance;
  private IOxBodyTechniqueConfiguration configuration;
  private HealthModelImpl health;
  private DummyHero hero;

  @SuppressWarnings("serial")
  @Before
  public void setUp() throws Exception {
    TraitValueStrategy strategy = new CreationTraitValueStrategy();
    hero = new BasicCharacterTestCase().createModelContextWithEssence2(strategy);
    resistance = createResistance(hero);
    TraitModel traitModel = TraitModelFetcher.fetch(hero);
    traitModel.addTraits(resistance);
    health = new HealthModelImpl();
    health.initialize(new DummyInitializationContext(hero), hero);
    configuration =
            new OxBodyTechniqueConfiguration(hero, null, new TraitType[]{resistance.getType()}, health.getOxBodyLearnArbitrator(), createObtCharm());
    health.getOxBodyLearnArbitrator().addOxBodyTechniqueConfiguration(configuration);
    health.addHealthLevelProvider(configuration.getHealthLevelProvider());
  }

  private DefaultTrait createResistance(DummyHero hero) {
    ITraitTemplate resistanceTemplate = SimpleTraitTemplate.createEssenceLimitedTemplate(0);
    FavorableTraitRules resistanceRules = new FavorableTraitRules(AbilityType.Resistance, resistanceTemplate, hero);
    CasteType[] castes = {new DummyCasteType()};
    return new DefaultTrait(hero, resistanceRules, castes, new FriendlyValueChangeChecker(), new FriendlyIncrementChecker());
  }

  @Test
  public void testOxBodyDecrease() {
    OxBodyCategory[] categories = configuration.getCategories();
    resistance.setCurrentValue(5);
    assertEquals(5, resistance.getCreationValue());
    categories[0].setCurrentValue(3);
    assertEquals(3, categories[0].getCreationValue());
    categories[1].setCurrentValue(2);
    assertEquals(2, categories[1].getCreationValue());
    resistance.setCurrentValue(0);
    assertEquals(0, resistance.getCreationValue());
    categories[0].setCurrentValue(0);
    assertEquals(0, categories[0].getCreationValue());
    categories[1].setCurrentValue(0);
    assertEquals(0, categories[1].getCreationValue());
  }

  @Test
  public void testTwoOxBodyTechniques() {
    @SuppressWarnings("serial") OxBodyTechniqueConfiguration secondConfiguration =
            new OxBodyTechniqueConfiguration(hero, null, new TraitType[]{resistance.getType()}, health.getOxBodyLearnArbitrator(), createObtCharm());
    health.getOxBodyLearnArbitrator().addOxBodyTechniqueConfiguration(secondConfiguration);
    health.addHealthLevelProvider(secondConfiguration.getHealthLevelProvider());
    resistance.setCurrentValue(2);
    configuration.getCategories()[0].setCurrentValue(2);
    assertEquals(2, configuration.getCategories()[0].getCreationValue());
    OxBodyCategory secondOxBodyTechnique = secondConfiguration.getCategories()[0];
    secondOxBodyTechnique.setCurrentValue(1);
    assertEquals(0, secondConfiguration.getCategories()[0].getCreationValue());
  }

  private OxBodyTechniqueCharm createObtCharm() {
    return new OxBodyTechniqueCharm("Abyssal.Ox-BodyTechnique", AbilityType.Resistance, new LinkedHashMap<String, HealthLevelType[]>() {
      {
        this.put("OxBody0", new HealthLevelType[]{HealthLevelType.ZERO});
        this.put("OxBody1", new HealthLevelType[]{HealthLevelType.ONE});
      }
    });
  }
}