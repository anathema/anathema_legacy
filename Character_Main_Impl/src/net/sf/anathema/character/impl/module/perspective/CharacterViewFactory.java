package net.sf.anathema.character.impl.module.perspective;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.IItemViewFactory;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.access.IRepositoryReadAccess;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.util.Identifier;

import static net.sf.anathema.character.impl.module.ExaltedCharacterItemTypeConfiguration.CHARACTER_ITEM_TYPE_ID;

public class CharacterViewFactory {

  private IAnathemaModel model;

  public CharacterViewFactory(IAnathemaModel model) {
    this.model = model;
  }

  public IView create(Identifier characterId) {
    IItem item = loadItem(characterId);
    return createView(item);
  }

  private IItem loadItem(Identifier characterId) {
    IRepositoryReadAccess readAccess = model.getRepository().openReadAccess(getItemType(), characterId.getId());
    return model.getPersisterRegistry().get(getItemType()).load(readAccess);
  }

  private IItemType getItemType() {
    return model.getItemTypeRegistry().getById(CHARACTER_ITEM_TYPE_ID);
  }

  public IView createView(IItem item) {
    IRegistry<IItemType, IItemViewFactory> registry = model.getViewFactoryRegistry();
    IItemViewFactory factory = registry.get(getItemType());
    return factory.createView(item);
  }
}
