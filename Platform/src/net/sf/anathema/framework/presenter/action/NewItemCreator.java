package net.sf.anathema.framework.presenter.action;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.workflow.wizard.selection.IAnathemaWizardModelTemplate;

public class NewItemCreator implements IItemCreator {

  private final IAnathemaModel anathemaModel;

  public NewItemCreator(IAnathemaModel anathemaModel) {
    this.anathemaModel = anathemaModel;
  }

  public IItem createItem(IItemType type, IAnathemaWizardModelTemplate template) throws PersistenceException {
    return anathemaModel.getPersisterRegistry().get(type).createNew(template);
  }
}