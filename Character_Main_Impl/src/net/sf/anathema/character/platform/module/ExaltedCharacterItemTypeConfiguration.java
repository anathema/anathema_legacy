package net.sf.anathema.character.platform.module;

import net.sf.anathema.character.CharacterPrintNameFileScanner;
import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.framework.CharacterGenericsExtractor;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.impl.persistence.ExaltedCharacterPersister;
import net.sf.anathema.character.itemtype.CharacterItemTypeRetrieval;
import net.sf.anathema.character.platform.module.repository.CharacterCreationTemplateFactory;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.module.AbstractPersistableItemTypeConfiguration;
import net.sf.anathema.framework.persistence.IRepositoryItemPersister;
import net.sf.anathema.framework.presenter.view.IItemTypeViewProperties;
import net.sf.anathema.framework.repository.IRepositoryFileResolver;
import net.sf.anathema.framework.repository.ItemType;
import net.sf.anathema.framework.repository.RepositoryConfiguration;
import net.sf.anathema.initialization.ItemTypeConfiguration;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.Resources;

@ItemTypeConfiguration
@Weight(weight = 0)
public class ExaltedCharacterItemTypeConfiguration extends AbstractPersistableItemTypeConfiguration {

  public ExaltedCharacterItemTypeConfiguration() throws AnathemaException {
    super(new ItemType(CharacterItemTypeRetrieval.CHARACTER_ITEM_TYPE_ID,
            new RepositoryConfiguration(".ecg", "ExaltedCharacter/")));
  }

  @Override
  protected IRepositoryItemPersister createPersister(IApplicationModel model) {
    return new ExaltedCharacterPersister(getItemType(), CharacterGenericsExtractor.getGenerics(model), model.getMessaging());
  }

  public final void registerViewFactory(IApplicationModel model, Resources resources) {
    // nothing to do
  }

  @Override
  protected IItemTypeViewProperties createItemTypeCreationProperties(IApplicationModel anathemaModel, Resources resources) {
    ICharacterGenerics generics = CharacterGenericsExtractor.getGenerics(anathemaModel);
    CharacterCreationTemplateFactory factory = new CharacterCreationTemplateFactory(generics, resources);
    IRegistry<ICharacterType, ICasteCollection> casteCollectionIRegistry = generics.getCasteCollectionRegistry();
    IRepositoryFileResolver fileResolver = anathemaModel.getRepository().getRepositoryFileResolver();
    CharacterPrintNameFileScanner scanner =
            new RegExCharacterPrintNameFileScanner(generics.getCharacterTypes(), casteCollectionIRegistry, fileResolver);
    return new CharacterViewProperties(getItemType(), resources, factory, scanner);
  }

}
