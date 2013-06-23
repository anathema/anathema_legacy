package net.sf.anathema.framework.presenter.action;

import net.sf.anathema.framework.repository.Item;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.workflow.wizard.selection.IDialogModelTemplate;

public interface IItemCreator {

  Item createItem(IDialogModelTemplate template) throws PersistenceException;
}