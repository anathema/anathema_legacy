package net.sf.anathema.framework.presenter.action;

import java.awt.Component;
import java.io.File;
import java.io.IOException;

import net.disy.commons.core.util.FileUtilities;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.workflow.wizard.selection.IAnathemaWizardModelTemplate;

public class ItemDeletionOperator implements IItemOperator {

  public void operate(Component parentComponent, IItemType type, IAnathemaWizardModelTemplate template)
      throws PersistenceException {
    try {
      File file = ((IFileProvider) template).getFile();
      FileUtilities.deleteFileOrDirectory(file);
    }
    catch (IOException e) {
      throw new PersistenceException("An exception occured while deleting.", e); //$NON-NLS-1$    
    }
  }
}