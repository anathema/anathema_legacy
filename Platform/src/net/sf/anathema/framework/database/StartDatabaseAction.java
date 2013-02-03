package net.sf.anathema.framework.database;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.itemdata.model.IItemData;
import net.sf.anathema.framework.message.MessageUtilities;
import net.sf.anathema.framework.module.IDatabaseActionProperties;
import net.sf.anathema.framework.presenter.IItemManagementModelListener;
import net.sf.anathema.framework.repository.AnathemaDataItem;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.dialog.message.MessageDialogFactory;
import net.sf.anathema.lib.gui.dialog.progress.ProgressMonitorDialog;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.progress.INonInterruptibleRunnableWithProgress;
import net.sf.anathema.lib.progress.IProgressMonitor;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.Identifier;

import javax.swing.Action;
import java.awt.Component;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class StartDatabaseAction extends SmartAction {

  private final IAnathemaModel anathemaModel;
  private final IResources resources;
  private final IDatabaseActionProperties properties;

  public StartDatabaseAction(IAnathemaModel anathemaModel, IResources resources, IDatabaseActionProperties properties) {
    this.anathemaModel = anathemaModel;
    this.resources = resources;
    this.properties = properties;
    anathemaModel.getItemManagement().addListener(new IItemManagementModelListener() {
      @Override
      public void itemAdded(IItem item) throws AnathemaException {
        if (isOwnItem(item)) {
          StartDatabaseAction.this.setEnabled(false);
        }
      }

      @Override
      public void itemSelected(IItem item) {
        // Nothing to do
      }

      @Override
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

  public static Action createMenuAction(IResources resources, IAnathemaModel anathemaModel,
                                        IDatabaseActionProperties properties) {
    StartDatabaseAction startDatabaseAction = new StartDatabaseAction(anathemaModel, resources, properties);
    startDatabaseAction.setName(properties.getActionName());
    return startDatabaseAction;
  }

  public static Action createToolAction(IResources resources, IAnathemaModel anathemaModel,
                                        IDatabaseActionProperties properties) {
    SmartAction action = new StartDatabaseAction(anathemaModel, resources, properties);
    action.setIcon(properties.getActionIcon());
    action.setToolTipText(properties.getToolTipText());
    return action;
  }

  @Override
  protected void execute(Component parentComponent) {
    try {
      new ProgressMonitorDialog(parentComponent, anathemaModel.generateInformativeMessage()).run(
              new INonInterruptibleRunnableWithProgress() {
                @Override
                public void run(IProgressMonitor monitor) throws InvocationTargetException {
                  try {
                    monitor.beginTaskWithUnknownTotalWork(properties.getProgressTaskTitle());
                    IItemType itemType = anathemaModel.getItemTypeRegistry().getById(properties.getItemTypeId());
                    IItemData database = properties.createItemData(anathemaModel.getRepository());
                    IItem anathemaItem = new AnathemaDataItem(itemType, new Identifier(properties.getItemId()),
                            database);
                    anathemaModel.getItemManagement().addItem(anathemaItem);
                  } catch (AnathemaException e) {
                    throw new InvocationTargetException(e);
                  }
                }
              });
    } catch (InvocationTargetException exception) {
      String text = resources.getString("Errors.Database.Launch"); //$NON-NLS-1$
      if (exception.getCause() instanceof IOException) {
        text = resources.getString("Errors.Database.CreatingRepositorySubDirectory"); //$NON-NLS-1$
      }
      MessageDialogFactory.showMessageDialog(parentComponent, new Message(text, exception));
    } catch (Throwable e) {
      MessageUtilities.indicateMessage(getClass(), parentComponent,
              new Message(resources.getString("Errors.Database.Launch"), e)); //$NON-NLS-1$
    }
  }
}