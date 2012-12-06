package net.sf.anathema;

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

  @org.junit.Test
  public void testName() throws Exception {
    IAnathemaModel model = initializeAnathema();
    ICharacterTemplate defaultSiderealTemplate = loadDefaultSiderealTemplate(model);
    ICharacter character = createCharacterFromTemplate(model, defaultSiderealTemplate);
    setCasteToSerenity(defaultSiderealTemplate, character);
    assertThatSevenDotsAreSpentOnFavoredAbilities(character);
  }

  private ICharacter createCharacterFromTemplate(IAnathemaModel model, ICharacterTemplate defaultSiderealTemplate) {
    CharacterStatisticsConfiguration template = createCreationInstructionsForDefaultSidereal(defaultSiderealTemplate);
    IRegistry<IItemType, IRepositoryItemPersister> persisterRegistry = model.getPersisterRegistry();
    IItemType characterItemType = model.getItemTypeRegistry().getById(CHARACTER_ITEM_TYPE_ID);
    IRepositoryItemPersister itemPersister = persisterRegistry.get(characterItemType);
    IItem item = itemPersister.createNew(template);
    return (ICharacter) item.getItemData();
  }

  private IAnathemaModel initializeAnathema() {
    TestInitializer initializer = new TestInitializer();
    return initializer.initialize();
  }

  private void assertThatSevenDotsAreSpentOnFavoredAbilities(ICharacter character) {
    BonusPointManagement bonusPointManagement = new BonusPointManagement(character);
    bonusPointManagement.recalculate();
    Integer dotsSpent = bonusPointManagement.getFavoredAbilityModel().getValue();
    assertThat(dotsSpent, is(7));
  }

  private void setCasteToSerenity(ICharacterTemplate template, ICharacter character) {
    ICasteCollection casteCollection = template.getCasteCollection();
    ICasteType serenityCaste = casteCollection.getById("Serenity");
    character.getCharacterConcept().getCaste().setType(serenityCaste);
  }

  private ICharacterTemplate loadDefaultSiderealTemplate(IAnathemaModel model) {
    ICharacterGenerics generics = CharacterGenericsExtractor.getGenerics(model);
    return generics.getTemplateRegistry().getDefaultTemplate(SIDEREAL);
  }

  private CharacterStatisticsConfiguration createCreationInstructionsForDefaultSidereal(ICharacterTemplate innerTemplate) {
    CharacterStatisticsConfiguration template = new CharacterStatisticsConfiguration();
    template.setTemplate(innerTemplate);
    return template;
  }
}