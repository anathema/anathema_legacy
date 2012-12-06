package net.sf.anathema;

import net.sf.anathema.character.generic.framework.CharacterGenericsExtractor;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.impl.model.CharacterStatisticsConfiguration;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.persistence.IRepositoryItemPersister;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.registry.IRegistry;

import java.util.Arrays;

import static net.sf.anathema.character.generic.type.CharacterType.SIDEREAL;
import static net.sf.anathema.character.impl.module.ExaltedCharacterItemTypeConfiguration.CHARACTER_ITEM_TYPE_ID;

public class Integrationtest {

  @org.junit.Test
  public void testName() throws Exception {
    TestInitializer initializer = new TestInitializer();
    IAnathemaModel model = initializer.initialize();
    CharacterStatisticsConfiguration template = createCreationInstructionsForDefaultSidereal(model);
    IRegistry<IItemType, IRepositoryItemPersister> persisterRegistry = model.getPersisterRegistry();
    IItemType characterItemType = model.getItemTypeRegistry().getById(CHARACTER_ITEM_TYPE_ID);
    IRepositoryItemPersister itemPersister = persisterRegistry.get(characterItemType);
    IItem item = itemPersister.createNew(template);
    ICharacter character = (ICharacter) item.getItemData();
    System.out.println(Arrays.deepToString(character.getTraitConfiguration().getAllAbilities()));
  }

  private CharacterStatisticsConfiguration createCreationInstructionsForDefaultSidereal(IAnathemaModel model) {
    CharacterStatisticsConfiguration template = new CharacterStatisticsConfiguration();
    ICharacterGenerics generics = CharacterGenericsExtractor.getGenerics(model);
    ICharacterTemplate siderealDefaultTemplate = generics.getTemplateRegistry().getDefaultTemplate(SIDEREAL);
    template.setTemplate(siderealDefaultTemplate);
    return template;
  }
}