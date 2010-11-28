package net.sf.anathema.framework.presenter.action;

import java.awt.Component;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.workflow.wizard.selection.IAnathemaWizardModelTemplate;

public interface IItemOperator {

  public void operate(Component parentComponent, IItemType type, IAnathemaWizardModelTemplate template)
      throws PersistenceException;
}