package net.sf.anathema.framework.presenter.action;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.workflow.wizard.selection.IAnathemaWizardModelTemplate;

import java.awt.Component;

public interface IItemOperator {

  void operate(Component parentComponent, IItemType type, IAnathemaWizardModelTemplate template)
      throws PersistenceException;
}