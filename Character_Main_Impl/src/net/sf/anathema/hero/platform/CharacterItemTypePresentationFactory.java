package net.sf.anathema.hero.platform;

import net.sf.anathema.character.main.framework.CharacterGenericsExtractor;
import net.sf.anathema.character.main.framework.HeroEnvironment;
import net.sf.anathema.character.main.framework.item.CharacterPrintNameFileScanner;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.module.ItemTypePresentationFactory;
import net.sf.anathema.framework.presenter.view.IItemTypeViewProperties;
import net.sf.anathema.framework.repository.IRepositoryFileResolver;
import net.sf.anathema.initialization.RegisteredItemTypePresentation;
import net.sf.anathema.lib.resources.Resources;

import static net.sf.anathema.character.main.itemtype.CharacterItemTypeRetrieval.retrieveCharacterItemType;
import static net.sf.anathema.character.main.itemtype.ExaltedCharacterItemTypeConfiguration.CHARACTER_ITEM_TYPE_ID;

@RegisteredItemTypePresentation(itemType = CHARACTER_ITEM_TYPE_ID)
public class CharacterItemTypePresentationFactory implements ItemTypePresentationFactory {

  @Override
  public IItemTypeViewProperties createItemTypeCreationProperties(IApplicationModel anathemaModel, Resources resources) {
    HeroEnvironment generics = CharacterGenericsExtractor.getGenerics(anathemaModel);
    IRepositoryFileResolver fileResolver = anathemaModel.getRepository().getRepositoryFileResolver();
    CharacterPrintNameFileScanner scanner = new RegExCharacterPrintNameFileScanner(generics.getCharacterTypes(), fileResolver);
    return new CharacterViewProperties(retrieveCharacterItemType(), resources, scanner);
  }
}