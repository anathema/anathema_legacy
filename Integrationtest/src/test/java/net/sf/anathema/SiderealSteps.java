package net.sf.anathema;

import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.framework.CharacterGenericsExtractor;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.type.CharacterTypes;
import net.sf.anathema.character.impl.model.CharacterStatisticsConfiguration;
import net.sf.anathema.character.impl.model.creation.bonus.BonusPointManagement;
import net.sf.anathema.character.library.trait.favorable.IFavorableTrait;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.background.IBackground;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.persistence.IRepositoryItemPersister;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.util.Identificate;

import static net.sf.anathema.character.impl.module.ExaltedCharacterItemTypeConfiguration.CHARACTER_ITEM_TYPE_ID;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SiderealSteps {
  private IAnathemaModel model;
  private ICharacter character;

  @Before
  public void startAnathema() {
    TestInitializer initializer = new TestInitializer();
    this.model = initializer.initialize();
  }

  @When("^I create a new default (.*)$")
  public void I_create_a_new_character(String type) throws Throwable {
    ICharacterTemplate characterTemplate = loadDefaultTemplateForType(type);
    this.character = createCharacter(characterTemplate);
  }

  @When("^I create a new (.*) using rules for (.*)$")
  public void I_create_a_new_character_with_subtype(String type, String subtype) throws Throwable {
    ICharacterTemplate characterTemplate = loadTemplateForType(type, subtype);
    this.character = createCharacter(characterTemplate);
  }

  @When("^I set her Caste to (.*)$")
  public void I_set_her_Caste(String casteName) throws Throwable {
    ICasteCollection casteCollection = character.getCharacterTemplate().getCasteCollection();
    ICasteType caste = casteCollection.getById(casteName);
    character.getCharacterConcept().getCaste().setType(caste);
  }

  @When("^she has the background (.*) at (\\d+)$")
  public void she_has_the_background_at(String name, int value) throws Throwable {
      IBackground background = character.getTraitConfiguration().getBackgrounds().addBackground(name, "");
      background.setCreationValue(value);
  }

  @Then("^she has spent (\\d+) bonus points$")
  public void she_has_spent_bonus_points(int amount) throws Throwable {
      BonusPointManagement bonusPointManagement = calculateBonusPoints();
      int spentBonusPoints = bonusPointManagement.getBackgroundModel().getSpentBonusPoints();
      assertThat(spentBonusPoints, is(amount));
  }

    @Then("^she has (\\d+) favored dots spent.$")
  public void she_has_favored_dots_spent(int amount) throws Throwable {
    BonusPointManagement bonusPointManagement = calculateBonusPoints();
    Integer dotsSpent = bonusPointManagement.getFavoredAbilityModel().getValue();
    assertThat(dotsSpent, is(amount));
  }

    @Then("^she has (\\d+) ability dots spent.$")
    public void she_has_ability_dots_spent(int amount) throws Throwable {
        BonusPointManagement bonusPointManagement = calculateBonusPoints();
        Integer dotsSpent = bonusPointManagement.getDefaultAbilityModel().getValue();
        assertThat(dotsSpent, is(amount));
    }

  @Then("^she has (\\d+) dots in (.*)$")
  public void she_has_dots_in_Craft(int amount, String abilityName) throws Throwable {
    IFavorableTrait ability = character.getTraitConfiguration().getFavorableTrait(AbilityType.valueOf(abilityName));
    assertThat(ability.getCurrentValue(), is(amount));
  }

  private ICharacterTemplate loadDefaultTemplateForType(String type) {
    ICharacterGenerics generics = CharacterGenericsExtractor.getGenerics(model);
    return generics.getTemplateRegistry().getDefaultTemplate(CharacterTypes.findById(type));
  }

  private ICharacterTemplate loadTemplateForType(String type, String subtype) {
    ICharacterGenerics generics = CharacterGenericsExtractor.getGenerics(model);
    return generics.getTemplateRegistry().getTemplate(new TemplateType(CharacterTypes.findById(type), new Identificate(subtype)));
  }

    private ICharacter createCharacter(ICharacterTemplate template) {
    CharacterStatisticsConfiguration creationRules = new CharacterStatisticsConfiguration();
    creationRules.setTemplate(template);
    IRegistry<IItemType, IRepositoryItemPersister> persisterRegistry = model.getPersisterRegistry();
    IItemType characterItemType = model.getItemTypeRegistry().getById(CHARACTER_ITEM_TYPE_ID);
    IRepositoryItemPersister itemPersister = persisterRegistry.get(characterItemType);
    IItem item = itemPersister.createNew(creationRules);
    return (ICharacter) item.getItemData();
  }
    private BonusPointManagement calculateBonusPoints() {
        BonusPointManagement bonusPointManagement = new BonusPointManagement(character);
        bonusPointManagement.recalculate();
        return bonusPointManagement;
    }

}