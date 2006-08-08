package net.sf.anathema.framework.presenter.action;

import java.awt.Component;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.message.Message;
import net.disy.commons.core.progress.IProgressMonitor;
import net.disy.commons.core.progress.IRunnableWithProgress;
import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.dialog.core.ISwingFrameOrDialog;
import net.disy.commons.swing.dialog.message.MessageDialogFactory;
import net.disy.commons.swing.dialog.progress.ProgressMonitorDialog;
import net.disy.commons.swing.dialog.wizard.WizardDialog;
import net.disy.commons.swing.util.GuiUtilities;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.message.MessageUtilities;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.gui.wizard.AnathemaWizardDialog;
import net.sf.anathema.lib.gui.wizard.IAnathemaWizardPage;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.wizard.selection.IAnathemaWizardModelTemplate;

public abstract class AbstractItemAction extends SmartAction {

  private final IResources resources;
  private final IAnathemaModel anathemaModel;
  private final IItemCreator creator;

  public AbstractItemAction(IAnathemaModel anathemaModel, IResources resources, IItemCreator creator) {
    this.anathemaModel = anathemaModel;
    this.resources = resources;
    this.creator = creator;
  }

  protected final boolean showDialog(Component parentComponent, IAnathemaWizardPage startPage) {
    WizardDialog dialog = new AnathemaWizardDialog(parentComponent, startPage);
    final ISwingFrameOrDialog configuredDialog = dialog.getConfiguredDialog();
    configuredDialog.setResizable(false);
    GuiUtilities.centerToParent(configuredDialog.getWindow());
    configuredDialog.show();
    return dialog.isCanceled();
  }

  protected final void createItem(Component parentComponent, IItemType type, IAnathemaWizardModelTemplate template)
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
            anathemaModel.getItemManagement().addItem(item);
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

  protected IAnathemaModel getAnathemaModel() {
    return anathemaModel;
  }

  protected IResources getResources() {
    return resources;
  }

  protected static IItemType[] collectItemTypes(IAnathemaModel model) {
    List<IItemType> types = new ArrayList<IItemType>();
    for (IItemType type : model.getItemTypeRegistry().getAllItemTypes()) {
      if (type.supportsRepository()) {
        types.add(type);
      }
    }
    return types.toArray(new IItemType[types.size()]);
  }
}