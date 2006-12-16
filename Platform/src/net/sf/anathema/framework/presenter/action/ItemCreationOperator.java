package net.sf.anathema.framework.presenter.action;

import java.awt.Component;
import java.lang.reflect.InvocationTargetException;

import net.disy.commons.core.message.Message;
import net.disy.commons.core.progress.IProgressMonitor;
import net.disy.commons.core.progress.IRunnableWithProgress;
import net.disy.commons.swing.dialog.message.MessageDialogFactory;
import net.disy.commons.swing.dialog.progress.ProgressMonitorDialog;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.message.MessageUtilities;
import net.sf.anathema.framework.presenter.IItemMangementModel;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.wizard.selection.IAnathemaWizardModelTemplate;

public class ItemCreationOperator implements IItemOperator {

  private final IItemCreator creator;
  private final IResources resources;
  private final IItemMangementModel model;

  public ItemCreationOperator(IItemCreator creator, IResources resources, IItemMangementModel model) {
    this.creator = creator;
    this.resources = resources;
    this.model = model;
  }

  public void operate(Component parentComponent, IItemType type, IAnathemaWizardModelTemplate template)
      throws PersistenceException {
    final IItem item = creator.createItem(type, template);
    final Object[] internationalizedType = new Object[] { resources.getString("ItemType." + item.getItemType() + ".PrintName") }; //$NON-NLS-1$ //$NON-NLS-2$
    String title = resources.getString("AnathemaCore.AddItem.Progress.Title", internationalizedType); //$NON-NLS-1$
    try {
      new ProgressMonitorDialog(parentComponent, title).run(false, new IRunnableWithProgress() {
        public void run(IProgressMonitor monitor) throws InterruptedException, InvocationTargetException {
          try {
            monitor.beginTask(
                resources.getString("AnathemaCore.AddItem.Progress.Task.Model", internationalizedType), IProgressMonitor.UNKNOWN); //$NON-NLS-1$
            monitor.beginTask(
                resources.getString("AnathemaCore.AddItem.Progress.Task.View", internationalizedType), IProgressMonitor.UNKNOWN); //$NON-NLS-1$
            model.addItem(item);
          }
          catch (AnathemaException e) {
            throw new InvocationTargetException(e);
          }
        }
      });
    }
    catch (InvocationTargetException exception) {
      Throwable cause = exception.getCause();
      MessageDialogFactory.showMessageDialog(parentComponent, new Message(
          "An error occured while creating repository item: " + cause.getMessage(), cause)); //$NON-NLS-1$
    }
    catch (Throwable e) {
      MessageUtilities.indicateMessage(getClass(), parentComponent, new Message(
          "An error occured while creating repository item.", e)); //$NON-NLS-1$
    }
  }
}