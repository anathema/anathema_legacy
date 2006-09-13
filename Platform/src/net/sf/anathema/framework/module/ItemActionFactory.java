package net.sf.anathema.framework.module;

import javax.swing.Action;

import net.sf.anathema.framework.model.IItemActionFactory;
import net.sf.anathema.framework.presenter.IItemMangementModel;
import net.sf.anathema.framework.presenter.itemmanagement.AnathemaItemCloseAction;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.resources.IResources;

public class ItemActionFactory implements IItemActionFactory {

  private final IItemMangementModel model;
  private final IResources resources;

  public ItemActionFactory(IItemMangementModel model, IResources resources) {
    this.model = model;
    this.resources = resources;
  }

  public Action createAction(IItem item) {
    return AnathemaItemCloseAction.createForItem(model, resources, item);
  }
}
