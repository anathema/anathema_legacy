package net.sf.anathema.hero.repositorytree;

import net.sf.anathema.character.framework.item.CharacterReferenceScanner;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.module.ItemTypePresentationFactory;
import net.sf.anathema.framework.presenter.view.IItemTypeViewProperties;
import net.sf.anathema.framework.repository.IRepositoryFileResolver;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.framework.HeroEnvironmentExtractor;
import net.sf.anathema.hero.platform.JsonCharacterReferenceScanner;
import net.sf.anathema.initialization.ForItemType;
import net.sf.anathema.framework.environment.Resources;

import static net.sf.anathema.character.framework.itemtype.CharacterItemTypeRetrieval.retrieveCharacterItemType;
import static net.sf.anathema.character.framework.itemtype.CharacterItemType.CHARACTER_ITEM_TYPE_ID;

@ForItemType(CHARACTER_ITEM_TYPE_ID)
public class CharacterItemTypePresentationFactory implements ItemTypePresentationFactory {

  @Override
  public IItemTypeViewProperties createItemTypeCreationProperties(IApplicationModel anathemaModel, Resources resources) {
    HeroEnvironment generics = HeroEnvironmentExtractor.getGenerics(anathemaModel);
    IRepositoryFileResolver fileResolver = anathemaModel.getRepository().getRepositoryFileResolver();
    CharacterReferenceScanner scanner = new JsonCharacterReferenceScanner(generics.getCharacterTypes(), fileResolver);
    return new CharacterViewProperties(retrieveCharacterItemType(), resources, scanner);
  }
}