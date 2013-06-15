package net.sf.anathema;

import com.google.inject.Inject;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import net.sf.anathema.character.generic.framework.CharacterGenericsExtractor;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.type.CharacterTypes;
import net.sf.anathema.character.impl.model.CharacterStatisticsConfiguration;
import net.sf.anathema.character.itemtype.CharacterItemTypeRetrieval;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.persistence.IRepositoryItemPersister;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class CharacterCreationSteps {
  private CharacterTypes characterTypes;
  private IApplicationModel model;
  private final CharacterHolder holder;

  @Inject
  public CharacterCreationSteps(CharacterHolder holder) {
    this.holder = holder;
  }

  @Before
  public void startAnathema() {
    TestInitializer initializer = new TestInitializer();
    this.model = initializer.initialize();
    this.characterTypes = CharacterGenericsExtractor.getGenerics(model).getCharacterTypes();
  }

  @Given("^a new default (.*)$")
  public void I_create_a_new_character(String type) throws Throwable {
    ICharacterTemplate characterTemplate = loadDefaultTemplateForType(type);
    holder.setCharacter(createCharacter(characterTemplate));
  }

  @Given("^a new God-Blooded of any kind$")
  public void I_create_a_new_god_blooded() throws Throwable {
    ICharacterTemplate characterTemplate = loadTemplateForType("Lunar", "HalfCasteLunar");
    holder.setCharacter(createCharacter(characterTemplate));
  }

  @Given("^a new (.*) using rules for (.*)$")
  public void I_create_a_new_character_with_subtype(String type, String subtype) throws Throwable {
    ICharacterTemplate characterTemplate = loadTemplateForType(type, subtype);
    holder.setCharacter(createCharacter(characterTemplate));
  }

  private ICharacterTemplate loadDefaultTemplateForType(String type) {
    ICharacterGenerics generics = CharacterGenericsExtractor.getGenerics(model);
    return generics.getTemplateRegistry().getDefaultTemplate(characterTypes.findById(type));
  }

  private ICharacterTemplate loadTemplateForType(String type, String subtype) {
    ICharacterGenerics generics = CharacterGenericsExtractor.getGenerics(model);
    return generics.getTemplateRegistry().getTemplate(new TemplateType(characterTypes.findById(type), new SimpleIdentifier(subtype)));
  }

  private ICharacter createCharacter(ICharacterTemplate template) {
    CharacterStatisticsConfiguration creationRules = new CharacterStatisticsConfiguration();
    creationRules.setTemplate(template);
    IRegistry<IItemType, IRepositoryItemPersister> persisterRegistry = model.getPersisterRegistry();
    IItemType characterItemType = model.getItemTypeRegistry().getById(CharacterItemTypeRetrieval.CHARACTER_ITEM_TYPE_ID);
    IRepositoryItemPersister itemPersister = persisterRegistry.get(characterItemType);
    IItem item = itemPersister.createNew(creationRules);
    return (ICharacter) item.getItemData();
  }
}