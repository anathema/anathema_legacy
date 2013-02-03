package net.sf.anathema.character.impl.module.perspective;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.persistence.IRepositoryItemPersister;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.IRepository;
import net.sf.anathema.framework.repository.access.IRepositoryReadAccess;
import net.sf.anathema.lib.registry.IRegistry;

public class CharacterSystemModel {

  private final IAnathemaModel model;

  public CharacterSystemModel(IAnathemaModel model) {
    this.model = model;
  }

  public IItemType getCharacterItemType() {
    return model.getItemTypeRegistry().getById("ExaltedCharacter");
  }

  public IItem loadItem(String repositoryId) {
    IItemType characterItemType = getCharacterItemType();
    IRepository repository = model.getRepository();
    IRepositoryReadAccess readAccess = repository.openReadAccess(characterItemType, repositoryId);
    IRegistry<IItemType,IRepositoryItemPersister> registry = model.getPersisterRegistry();
    IRepositoryItemPersister persister = registry.get(characterItemType);
    return persister.load(readAccess);
  }
}
