package net.sf.anathema.character.platform.module;

import net.sf.anathema.character.CharacterPrintNameFileScanner;
import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.framework.CharacterGenericsExtractor;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.module.ItemTypePresentationFactory;
import net.sf.anathema.framework.presenter.view.IItemTypeViewProperties;
import net.sf.anathema.framework.repository.IRepositoryFileResolver;
import net.sf.anathema.initialization.RegisteredItemTypePresentation;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.Resources;

import static net.sf.anathema.character.itemtype.CharacterItemTypeRetrieval.CHARACTER_ITEM_TYPE_ID;
import static net.sf.anathema.character.itemtype.CharacterItemTypeRetrieval.retrieveCharacterItemType;

@RegisteredItemTypePresentation(itemType = CHARACTER_ITEM_TYPE_ID)
public class CharacterItemTypePresentationFactory implements ItemTypePresentationFactory {

  @Override
  public IItemTypeViewProperties createItemTypeCreationProperties(IApplicationModel anathemaModel, Resources resources) {
    ICharacterGenerics generics = CharacterGenericsExtractor.getGenerics(anathemaModel);
    IRegistry<ICharacterType, ICasteCollection> casteCollectionIRegistry = generics.getCasteCollectionRegistry();
    IRepositoryFileResolver fileResolver = anathemaModel.getRepository().getRepositoryFileResolver();
    CharacterPrintNameFileScanner scanner = new RegExCharacterPrintNameFileScanner(generics.getCharacterTypes(), casteCollectionIRegistry, fileResolver);
    return new CharacterViewProperties(retrieveCharacterItemType(anathemaModel), resources, scanner);
  }
}
