package net.sf.anathema.hero.framework.perspective.model;

import net.sf.anathema.character.main.framework.item.ItemRepositoryLocation;
import net.sf.anathema.character.main.itemtype.CharacterItemTypeRetrieval;
import net.sf.anathema.character.main.persistence.HeroItemPersister;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.persistence.RepositoryItemPersister;
import net.sf.anathema.character.main.framework.item.Item;
import net.sf.anathema.framework.repository.Repository;
import net.sf.anathema.framework.repository.access.RepositoryReadAccess;
import net.sf.anathema.framework.repository.access.RepositoryWriteAccess;
import net.sf.anathema.framework.repository.access.printname.PrintNameFileAccess;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.hero.framework.HeroEnvironment;

import java.io.IOException;
import java.util.Collection;

import static net.sf.anathema.character.main.itemtype.CharacterItemTypeRetrieval.retrieveCharacterItemType;

public class CharacterPersistenceModel {

  private IApplicationModel model;
  private HeroEnvironment heroEnvironment;

  public CharacterPersistenceModel(IApplicationModel model, HeroEnvironment environment) {
    this.model = model;
    this.heroEnvironment = environment;
  }

  public Collection<PrintNameFile> collectCharacterPrintNameFiles() {
    IItemType characterItemType = getCharacterItemType();
    PrintNameFileAccess access = model.getRepository().getPrintNameFileAccess();
    return access.collectAllPrintNameFiles(characterItemType);
  }

  public Item loadItem(CharacterIdentifier identifier) {
    RepositoryReadAccess readAccess = createReadAccess(identifier.getId());
    RepositoryItemPersister persister = findPersister();
    return persister.load(readAccess);
  }

  public void save(Item item) throws IOException {
    RepositoryItemPersister persister = findPersister();
    assignUniqueIdAsRequired(item);
    RepositoryWriteAccess writeAccess = createWriteAccessFor(item);
    persister.save(writeAccess, item);
  }

  private void assignUniqueIdAsRequired(Item item) {
    ItemRepositoryLocation repositoryLocation = item.getRepositoryLocation();
    if (repositoryLocation.getId() == null) {
      Repository repository = model.getRepository();
      String id = repository.createUniqueRepositoryId(repositoryLocation);
      repositoryLocation.setId(id);
    }
  }

  private RepositoryWriteAccess createWriteAccessFor(Item item) {
    return model.getRepository().createWriteAccess(CharacterItemTypeRetrieval.retrieveCharacterItemType(), item.getRepositoryLocation().getId());
  }

  private RepositoryItemPersister findPersister() {
    HeroEnvironment generics = heroEnvironment;
    return new HeroItemPersister(generics, model.getMessaging());
  }

  private RepositoryReadAccess createReadAccess(String repositoryId) {
    Repository repository = model.getRepository();
    return repository.openReadAccess(getCharacterItemType(), repositoryId);
  }

  private IItemType getCharacterItemType() {
    return retrieveCharacterItemType();
  }
}
