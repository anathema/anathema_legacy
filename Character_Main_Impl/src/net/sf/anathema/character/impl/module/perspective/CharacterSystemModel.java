package net.sf.anathema.character.impl.module.perspective;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.persistence.IRepositoryItemPersister;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.IRepository;
import net.sf.anathema.framework.repository.access.IRepositoryReadAccess;
import net.sf.anathema.lib.registry.IRegistry;

import static net.sf.anathema.character.itemtype.CharacterItemTypeRetrieval.retrieveCharacterItemType;

public class CharacterSystemModel {

  private final IAnathemaModel model;

  public CharacterSystemModel(IAnathemaModel model) {
    this.model = model;
  }

  public IItem loadItem(String repositoryId) {
    IRepositoryReadAccess readAccess = createReadAccess(repositoryId);
    IRepositoryItemPersister persister = extractPersister();
    return persister.load(readAccess);
  }

  private IRepositoryItemPersister extractPersister() {
    IRegistry<IItemType,IRepositoryItemPersister> registry = model.getPersisterRegistry();
    return registry.get(getCharacterItemType());
  }

  private IRepositoryReadAccess createReadAccess(String repositoryId) {
    IRepository repository = model.getRepository();
    return repository.openReadAccess(getCharacterItemType(), repositoryId);
  }

  private IItemType getCharacterItemType() {
    return retrieveCharacterItemType(model);
  }
}
