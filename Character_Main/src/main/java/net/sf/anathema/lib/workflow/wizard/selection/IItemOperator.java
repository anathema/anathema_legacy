package net.sf.anathema.lib.workflow.wizard.selection;

import net.sf.anathema.lib.exception.PersistenceException;

public interface IItemOperator {

  void operate(IDialogModelTemplate template)
      throws PersistenceException;
}