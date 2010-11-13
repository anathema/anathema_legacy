package net.sf.anathema.platform.database;

import java.awt.Component;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.Action;

import net.disy.commons.core.message.Message;
import net.disy.commons.core.progress.IProgressMonitor;
import net.disy.commons.core.progress.IRunnableWithProgress;
import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.dialog.message.MessageDialogFactory;
import net.disy.commons.swing.dialog.progress.ProgressMonitorDialog;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.itemdata.model.IItemData;
import net.sf.anathema.framework.message.MessageUtilities;
import net.sf.anathema.framework.module.IDatabaseActionProperties;
import net.sf.anathema.framework.presenter.IItemManagementModelListener;
import net.sf.anathema.framework.repository.AnathemaDataItem;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.Identificate;

import com.db4o.ext.DatabaseFileLockedException;

public class StartDatabaseAction extends SmartAction {

  private final IAnathemaModel anathemaModel;
  private final IResources resources;
  private final IDatabaseActionProperties properties;

  public StartDatabaseAction(IAnathemaModel anathemaModel, IResources resources, IDatabaseActionProperties properties) {
    this.anathemaModel = anathemaModel;
    this.resources = resources;
    this.properties = properties;
    anathemaModel.getItemManagement().addListener(new IItemManagementModelListener() {
      public void itemAdded(IItem item) throws AnathemaException {
        if (isOwnItem(item)) {
          StartDatabaseAction.this.setEnabled(false);
        }
      }

      public void itemSelected(IItem item) {
        // Nothing to do
      }

      public void itemRemoved(IItem item) {
        if (isOwnItem(item)) {
          StartDatabaseAction.this.setEnabled(true);
        }
      }
    });
  }

  private boolean isOwnItem(IItem item) {
    return item.getItemType().getId().equals(properties.getItemTypeId());
  }

  public static Action createMenuAction(
      IResources resources,
      IAnathemaModel anathemaModel,
      IDatabaseActionProperties properties) {
    StartDatabaseAction startDatabaseAction = new StartDatabaseAction(anathemaModel, resources, properties);
    startDatabaseAction.setName(properties.getActionName());
    return startDatabaseAction;
  }

  public static Action createToolAction(
      IResources resources,
      IAnathemaModel anathemaModel,
      IDatabaseActionProperties properties) {
    SmartAction action = new StartDatabaseAction(anathemaModel, resources, properties);
    action.setIcon(properties.getActionIcon());
    action.setToolTipText(properties.getToolTipText());
    return action;
  }

  @Override
  protected void execute(final Component parentComponent) {
    try {
      String title = properties.getProgressMonitorTitle();
      new ProgressMonitorDialog(parentComponent, title).run(false, new IRunnableWithProgress() {
        public void run(IProgressMonitor monitor) throws InterruptedException, InvocationTargetException {
          try {
            monitor.beginTask(properties.getProgressTaskTitle(), IProgressMonitor.UNKNOWN);
            IItemType itemType = anathemaModel.getItemTypeRegistry().getById(properties.getItemTypeId());
            IItemData database = properties.createItemData(anathemaModel.getRepository());
            IItem anathemaItem = new AnathemaDataItem(itemType, new Identificate(properties.getItemId()), database);
            anathemaModel.getItemManagement().addItem(anathemaItem);
          }
          catch (DatabaseFileLockedException e) {
            throw new InvocationTargetException(e);
          }
          catch (IOException e) {
            throw new InvocationTargetException(e);
          }
          catch (AnathemaException e) {
            throw new InvocationTargetException(e);
          }
        }
      });
    }
    catch (InvocationTargetException exception) {
      String text = resources.getString("Errors.Database.Launch"); //$NON-NLS-1$
      if (exception.getCause() instanceof DatabaseFileLockedException) {
        text = resources.getString("Errors.Database.FileLocked"); //$NON-NLS-1$
      }
      if (exception.getCause() instanceof IOException) {
        text = resources.getString("Errors.Database.CreatingRepositorySubDirectory"); //$NON-NLS-1$
      }
      MessageDialogFactory.showMessageDialog(parentComponent, new Message(text, exception));
    }
    catch (Throwable e) {
      MessageUtilities.indicateMessage(getClass(), parentComponent, new Message(
          resources.getString("Errors.Database.Launch"), e)); //$NON-NLS-1$
    }
  }
}