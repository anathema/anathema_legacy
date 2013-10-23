package net.sf.anathema.hero.framework.perspective.model;

import net.sf.anathema.character.main.framework.item.HeroRepositoryData;
import net.sf.anathema.character.main.framework.item.Item;
import net.sf.anathema.character.main.framework.item.ItemRepositoryLocation;
import net.sf.anathema.character.main.itemtype.CharacterItemTypeRetrieval;
import net.sf.anathema.character.main.persistence.HeroItemPersister;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.persistence.RepositoryItemPersister;
import net.sf.anathema.framework.repository.Repository;
import net.sf.anathema.framework.repository.RepositoryIdData;
import net.sf.anathema.framework.repository.access.RepositoryReadAccess;
import net.sf.anathema.framework.repository.access.RepositoryWriteAccess;
import net.sf.anathema.framework.repository.access.printname.ReferenceAccess;
import net.sf.anathema.framework.repository.access.printname.ReferenceBuilder;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.model.Hero;

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

  public Collection<CharacterReference> collectCharacters() {
    ReferenceBuilder<CharacterReference> builder = new CharacterReferenceBuilder();
    ReferenceAccess<CharacterReference> access = model.getRepository().createReferenceAccess(retrieveCharacterItemType(), builder);
    return access.collectAllItemReferences();
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
    if (repositoryLocation.requiresId()) {
      Repository repository = model.getRepository();
      RepositoryIdData data = new HeroRepositoryData((Hero) item.getItemData());
      String id = repository.createUniqueRepositoryId(data);
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
