package net.sf.anathema.framework.presenter.action;

import java.awt.Component;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.Icon;

import net.disy.commons.core.message.Message;
import net.disy.commons.core.progress.IProgressMonitor;
import net.disy.commons.core.progress.IRunnableWithProgress;
import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.dialog.progress.ProgressMonitorDialog;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.message.MessageUtilities;
import net.sf.anathema.framework.presenter.ItemManagementModelAdapter;
import net.sf.anathema.framework.presenter.resources.PlatformUI;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.IRepositoryFileChooser;
import net.sf.anathema.framework.repository.access.IRepositoryReadAccess;
import net.sf.anathema.framework.repository.filechooser.RepositoryFileChooserPropertiesExtensionPoint;
import net.sf.anathema.framework.view.RepositoryFileChooser;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public final class ItemTypeLoadAction extends AbstractItemAction {

  private final IItemType itemType;
  private final RepositoryFileChooserPropertiesExtensionPoint propertiesExtensionPoint;

  public static Action[] createToolActions(IAnathemaModel model, IResources resources) {
    List<Action> actions = new ArrayList<Action>();
    for (IItemType type : collectItemTypes(model)) {
      SmartAction action = createToolAction(model, type, resources);
      action.setName(resources.getString("ItemType." + type.getId() + ".PrintName")); //$NON-NLS-1$ //$NON-NLS-2$
      actions.add(action);
    }
    return actions.toArray(new Action[actions.size()]);
  }

  private static SmartAction createToolAction(IAnathemaModel anathemaModel, IItemType itemType, IResources resources) {
    IRegistry<String, IAnathemaExtension> extensionPointRegistry = anathemaModel.getExtensionPointRegistry();
    RepositoryFileChooserPropertiesExtensionPoint repositoryExtensionPoint = (RepositoryFileChooserPropertiesExtensionPoint) extensionPointRegistry.get(RepositoryFileChooserPropertiesExtensionPoint.ID);
    SmartAction action = new ItemTypeLoadAction(anathemaModel, itemType, resources, repositoryExtensionPoint);
    return action;
  }

  public static Icon getButtonIcon(IResources resources) {
    return new PlatformUI(resources).getLoadToolBarIcon();
  }

  public static String createToolTip(IResources resources) {
    return resources.getString("AnathemaPersistence.LoadMenu.Name"); //$NON-NLS-1$
  }

  private ItemTypeLoadAction(
      IAnathemaModel anathemaModel,
      IItemType itemType,
      IResources resources,
      RepositoryFileChooserPropertiesExtensionPoint propertiesExtensionPoint) {
    super(anathemaModel, resources);
    this.itemType = itemType;
    this.propertiesExtensionPoint = propertiesExtensionPoint;
    adjustEnabled();
    anathemaModel.getItemManagement().addListener(new ItemManagementModelAdapter() {
      @Override
      public void itemRemoved(IItem item) {
        adjustEnabled();
      }

      @Override
      public void itemAdded(IItem item) {
        adjustEnabled();
      }
    });
  }

  private void adjustEnabled() {
    setEnabled(getAnathemaModel().getRepository().containsClosed(itemType));
  }

  @Override
  protected void execute(Component parentComponent) {
    try {
      IRepositoryFileChooser fileChooser = new RepositoryFileChooser(getAnathemaModel().getRepository()
          .getPrintNameFileAccess(), getAnathemaModel().getItemManagement(), propertiesExtensionPoint);
      final File file = fileChooser.getRepositoryFile(itemType);
      final IRepositoryReadAccess readAccess = getAnathemaModel().getRepository().openReadAccess(
          itemType,
          new IFileProvider() {
            public File getFile() {
              return file;
            }
          });
      final String itemTypeDisplay = getResources().getString("ItemType." + itemType.getId() + ".PrintName"); //$NON-NLS-1$ //$NON-NLS-2$
      String title = getResources().getString(
          "AnathemaPersistence.LoadAction.Progress.Title", new Object[] { itemTypeDisplay }); //$NON-NLS-1$
      new ProgressMonitorDialog(parentComponent, title).run(false, new IRunnableWithProgress() {
        public void run(IProgressMonitor monitor) throws InterruptedException, InvocationTargetException {
          try {
            monitor.beginTask(getResources().getString("AnathemaPersistence.LoadAction.Progress.Model", //$NON-NLS-1$
                new Object[] { itemTypeDisplay }), IProgressMonitor.UNKNOWN);
            IItem item = getAnathemaModel().getPersisterRegistry().get(itemType).load(readAccess);
            if (item == null) {
              return;
            }
            monitor.beginTask(getResources().getString("AnathemaPersistence.LoadAction.Progress.View", //$NON-NLS-1$
                new Object[] { item.getDisplayName() }), IProgressMonitor.UNKNOWN);
            getAnathemaModel().getItemManagement().addItem(item);
          }
          catch (AnathemaException e) {
            throw new InvocationTargetException(e);
          }
        }
      });
    }
    catch (InvocationTargetException e) {
      String message = getResources().getString("AnathemaPersistence.LoadAction.Message.Error"); //$NON-NLS-1$
      MessageUtilities.indicateMessage(getClass(), parentComponent, new Message(message, e.getCause()));
    }
    catch (Throwable e) {
      String message = getResources().getString("AnathemaPersistence.LoadAction.Message.Error"); //$NON-NLS-1$
      MessageUtilities.indicateMessage(getClass(), parentComponent, new Message(message, e));
    }
  }
}