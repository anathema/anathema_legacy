package net.sf.anathema.framework.presenter.action;

import java.awt.Component;
import java.lang.reflect.InvocationTargetException;

import net.disy.commons.core.message.Message;
import net.disy.commons.core.progress.IProgressMonitor;
import net.disy.commons.core.progress.IRunnableWithProgress;
import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.dialog.message.MessageDialogFactory;
import net.disy.commons.swing.dialog.progress.ProgressMonitorDialog;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.item.IItemTypeRegistry;
import net.sf.anathema.framework.message.MessageUtilities;
import net.sf.anathema.framework.presenter.IItemMangementModel;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractAddNewItemAction<T> extends SmartAction {

  private final IItemMangementModel itemManagement;
  private final IItemTypeRegistry itemTypeModel;
  private final String itemTypeId;
  private final IResources resources;

  public AbstractAddNewItemAction(IAnathemaModel anathemaModel, IResources resources, String itemTypeId) {
    this.resources = resources;
    this.itemTypeId = itemTypeId;
    this.itemManagement = anathemaModel.getItemManagement();
    this.itemTypeModel = anathemaModel.getItemTypeRegistry();
  }

  @Override
  protected final void execute(Component parentComponent) {
    try {
      final T template = createTemplate(parentComponent);
      if (template == null) {
        return;
      }
      final Object[] internationalizedType = new Object[] { resources.getString("ItemType." + itemTypeId + ".PrintName") }; //$NON-NLS-1$ //$NON-NLS-2$
      String title = resources.getString("AnathemaCore.AddItem.Progress.Title", internationalizedType); //$NON-NLS-1$
      new ProgressMonitorDialog(parentComponent, title).run(false, new IRunnableWithProgress() {
        public void run(IProgressMonitor monitor) throws InterruptedException, InvocationTargetException {
          try {
            monitor.beginTask(
                resources.getString("AnathemaCore.AddItem.Progress.Task.Model", internationalizedType), IProgressMonitor.UNKNOWN); //$NON-NLS-1$
            final IItem item = createNewItem(template, itemTypeModel.getById(itemTypeId));
            monitor.beginTask(
                resources.getString("AnathemaCore.AddItem.Progress.Task.View", internationalizedType), IProgressMonitor.UNKNOWN); //$NON-NLS-1$
            itemManagement.addItem(item);
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

  protected abstract T createTemplate(Component parentComponent);

  protected abstract IItem createNewItem(T template, IItemType itemType) throws AnathemaException;
}
