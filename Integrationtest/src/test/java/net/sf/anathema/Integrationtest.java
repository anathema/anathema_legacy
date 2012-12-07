package net.sf.anathema;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.framework.CharacterGenericsExtractor;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.impl.model.CharacterStatisticsConfiguration;
import net.sf.anathema.character.impl.model.creation.bonus.BonusPointManagement;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.persistence.IRepositoryItemPersister;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.registry.IRegistry;

import static net.sf.anathema.character.generic.type.CharacterType.SIDEREAL;
import static net.sf.anathema.character.impl.module.ExaltedCharacterItemTypeConfiguration.CHARACTER_ITEM_TYPE_ID;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Integrationtest {
  private IAnathemaModel model;
  private ICharacter character;
  private ICharacterTemplate defaultSiderealTemplate;

  @Before
  public void startAnathema() {
    TestInitializer initializer = new TestInitializer();
    this.model = initializer.initialize();
  }

  @When("^I create a new Sidereal$")
  public void I_create_a_new_Sidereal() throws Throwable {
    this.defaultSiderealTemplate = loadDefaultSiderealTemplate(model);
    this.character = createCharacterFromTemplate(model, defaultSiderealTemplate);
  }

  @And("^I set her Caste to Serenity$")
  public void I_set_her_Caste_to_Serenity() throws Throwable {
    ICasteCollection casteCollection = defaultSiderealTemplate.getCasteCollection();
    ICasteType serenityCaste = casteCollection.getById("Serenity");
    character.getCharacterConcept().getCaste().setType(serenityCaste);
  }

  @Then("^she has (\\d+) favored dots spent.$")
  public void she_has_favored_dots_spent(int arg1) throws Throwable {
    BonusPointManagement bonusPointManagement = new BonusPointManagement(character);
    bonusPointManagement.recalculate();
    Integer dotsSpent = bonusPointManagement.getFavoredAbilityModel().getValue();
    assertThat(dotsSpent, is(7));
  }

  private ICharacter createCharacterFromTemplate(IAnathemaModel model, ICharacterTemplate defaultSiderealTemplate) {
    CharacterStatisticsConfiguration template = createCreationInstructionsForDefaultSidereal(defaultSiderealTemplate);
    IRegistry<IItemType, IRepositoryItemPersister> persisterRegistry = model.getPersisterRegistry();
    IItemType characterItemType = model.getItemTypeRegistry().getById(CHARACTER_ITEM_TYPE_ID);
    IRepositoryItemPersister itemPersister = persisterRegistry.get(characterItemType);
    IItem item = itemPersister.createNew(template);
    return (ICharacter) item.getItemData();
  }

  private ICharacterTemplate loadDefaultSiderealTemplate(IAnathemaModel model) {
    ICharacterGenerics generics = CharacterGenericsExtractor.getGenerics(model);
    return generics.getTemplateRegistry().getDefaultTemplate(SIDEREAL);
  }

  private CharacterStatisticsConfiguration createCreationInstructionsForDefaultSidereal(
          ICharacterTemplate innerTemplate) {
    CharacterStatisticsConfiguration template = new CharacterStatisticsConfiguration();
    template.setTemplate(innerTemplate);
    return template;
  }
}