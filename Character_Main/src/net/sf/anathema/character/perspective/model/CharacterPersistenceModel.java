package net.sf.anathema.character.perspective.model;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.persistence.IRepositoryItemPersister;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.IRepository;
import net.sf.anathema.framework.repository.access.IRepositoryReadAccess;
import net.sf.anathema.framework.repository.access.IRepositoryWriteAccess;
import net.sf.anathema.framework.repository.access.printname.IPrintNameFileAccess;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.registry.IRegistry;

import java.io.IOException;
import java.util.Collection;

import static net.sf.anathema.character.itemtype.CharacterItemTypeRetrieval.retrieveCharacterItemType;

public class CharacterPersistenceModel {

  private IApplicationModel model;

  public CharacterPersistenceModel(IApplicationModel model) {
    this.model = model;
  }

  public Collection<PrintNameFile> collectCharacterPrintNameFiles() {
    IItemType characterItemType = getCharacterItemType();
    IPrintNameFileAccess access = model.getRepository().getPrintNameFileAccess();
    return access.collectAllPrintNameFiles(characterItemType);
  }

  public IItem loadItem(CharacterIdentifier identifier) {
    IRepositoryReadAccess readAccess = createReadAccess(identifier.getId());
    IRepositoryItemPersister persister = findPersister();
    return persister.load(readAccess);
  }

  private IRepositoryItemPersister findPersister() {
    IRegistry<IItemType, IRepositoryItemPersister> registry = model.getPersisterRegistry();
    return registry.get(getCharacterItemType());
  }

  private IRepositoryReadAccess createReadAccess(String repositoryId) {
    IRepository repository = model.getRepository();
    return repository.openReadAccess(getCharacterItemType(), repositoryId);
  }

  private IItemType getCharacterItemType() {
    return retrieveCharacterItemType(model);
  }

  public void save(IItem item) throws IOException {
    IRepositoryItemPersister persister = findPersister();
    IRepository repository = model.getRepository();
    IRepositoryWriteAccess writeAccess = repository.createWriteAccess(item);
    persister.save(writeAccess, item);
  }
}
