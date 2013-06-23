package net.sf.anathema.framework.presenter.action;

import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.workflow.wizard.selection.IDialogModelTemplate;

public interface IItemOperator {

  void operate(IDialogModelTemplate template)
      throws PersistenceException;
}