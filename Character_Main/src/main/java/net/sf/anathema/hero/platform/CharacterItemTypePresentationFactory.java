package net.sf.anathema.hero.platform;

import net.sf.anathema.character.main.framework.item.CharacterPrintNameFileScanner;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.module.ItemTypePresentationFactory;
import net.sf.anathema.framework.presenter.view.IItemTypeViewProperties;
import net.sf.anathema.framework.repository.IRepositoryFileResolver;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.framework.HeroEnvironmentExtractor;
import net.sf.anathema.initialization.RegisteredItemTypePresentation;
import net.sf.anathema.framework.environment.Resources;

import static net.sf.anathema.character.main.itemtype.CharacterItemTypeRetrieval.retrieveCharacterItemType;
import static net.sf.anathema.character.main.itemtype.CharacterItemType.CHARACTER_ITEM_TYPE_ID;

@RegisteredItemTypePresentation(itemType = CHARACTER_ITEM_TYPE_ID)
public class CharacterItemTypePresentationFactory implements ItemTypePresentationFactory {

  @Override
  public IItemTypeViewProperties createItemTypeCreationProperties(IApplicationModel anathemaModel, Resources resources) {
    HeroEnvironment generics = HeroEnvironmentExtractor.getGenerics(anathemaModel);
    IRepositoryFileResolver fileResolver = anathemaModel.getRepository().getRepositoryFileResolver();
    CharacterPrintNameFileScanner scanner = new JsonCharacterPrintNameFileScanner(generics.getCharacterTypes(), fileResolver);
    return new CharacterViewProperties(retrieveCharacterItemType(), resources, scanner);
  }
}