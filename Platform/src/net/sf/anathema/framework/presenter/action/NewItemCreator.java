package net.sf.anathema.framework.presenter.action;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.workflow.wizard.selection.IDialogModelTemplate;

public class NewItemCreator implements IItemCreator {

  private final IApplicationModel anathemaModel;

  public NewItemCreator(IApplicationModel anathemaModel) {
    this.anathemaModel = anathemaModel;
  }

  @Override
  public IItem createItem(IItemType type, IDialogModelTemplate template) throws PersistenceException {
    IItem item = anathemaModel.getPersisterRegistry().get(type).createNew(template);
    item.setClean();
    return item;
  }
}