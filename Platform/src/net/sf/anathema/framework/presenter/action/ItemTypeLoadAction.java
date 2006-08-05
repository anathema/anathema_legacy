package net.sf.anathema.framework.presenter.action;

import java.awt.Component;
import java.awt.Cursor;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.Icon;

import net.disy.commons.core.io.IOUtilities;
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
import net.sf.anathema.framework.presenter.item.ItemTypeViewPropertiesExtensionPoint;
import net.sf.anathema.framework.presenter.resources.PlatformUI;
import net.sf.anathema.framework.presenter.view.IItemTypeViewProperties;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.IRepositoryFileChooser;
import net.sf.anathema.framework.repository.access.IRepositoryReadAccess;
import net.sf.anathema.framework.repository.filechooser.RepositoryFileChooserPropertiesExtensionPoint;
import net.sf.anathema.framework.view.RepositoryFileChooser;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public final class ItemTypeLoadAction extends SmartAction {

  private final IResources resources;
  private final IAnathemaModel anathemaModel;
  private final IItemType itemType;
  private final RepositoryFileChooserPropertiesExtensionPoint propertiesExtensionPoint;

  public static Action createMenuAction(IAnathemaModel anathemaModel, IItemType itemType, IResources resources) {
    IRegistry<String, IAnathemaExtension> extensionPointRegistry = anathemaModel.getExtensionPointRegistry();
    RepositoryFileChooserPropertiesExtensionPoint repositoryExtensionPoint = (RepositoryFileChooserPropertiesExtensionPoint) extensionPointRegistry.get(RepositoryFileChooserPropertiesExtensionPoint.ID);
    ItemTypeViewPropertiesExtensionPoint itemExtensionPoint = (ItemTypeViewPropertiesExtensionPoint) extensionPointRegistry.get(ItemTypeViewPropertiesExtensionPoint.ID);
    IItemTypeViewProperties properties = itemExtensionPoint.get(itemType);
    SmartAction action = new ItemTypeLoadAction(anathemaModel, itemType, resources, repositoryExtensionPoint);
    action.setName(properties == null ? itemType.getId() : properties.getPrintName());
    return action;
  }

  private static SmartAction createToolAction(IAnathemaModel anathemaModel, IItemType itemType, IResources resources) {
    IRegistry<String, IAnathemaExtension> extensionPointRegistry = anathemaModel.getExtensionPointRegistry();
    RepositoryFileChooserPropertiesExtensionPoint repositoryExtensionPoint = (RepositoryFileChooserPropertiesExtensionPoint) extensionPointRegistry.get(RepositoryFileChooserPropertiesExtensionPoint.ID);
    SmartAction action = new ItemTypeLoadAction(anathemaModel, itemType, resources, repositoryExtensionPoint);
    return action;
  }

  public static Action[] createToolActions(IAnathemaModel model, IResources resources) {
    List<Action> actions = new ArrayList<Action>();
    for (IItemType type : collectItemTypes(model)) {
      SmartAction action = createToolAction(model, type, resources);
      action.setName(resources.getString("ItemType." + type.getId() + ".PrintName")); //$NON-NLS-1$ //$NON-NLS-2$
      actions.add(action);
    }
    return actions.toArray(new Action[actions.size()]);
  }

  public static Icon getButtonIcon(IResources resources) {
    return new PlatformUI(resources).getLoadToolBarIcon();
  }

  public static String createToolTip(IResources resources) {
    return resources.getString("AnathemaCore.Tools.LoadMenu.Name"); //$NON-NLS-1$
  }

  private ItemTypeLoadAction(
      IAnathemaModel anathemaModel,
      IItemType itemType,
      IResources resources,
      RepositoryFileChooserPropertiesExtensionPoint propertiesExtensionPoint) {
    this.itemType = itemType;
    this.propertiesExtensionPoint = propertiesExtensionPoint;
    this.resources = resources;
    this.anathemaModel = anathemaModel;
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
    setEnabled(anathemaModel.getRepository().containsClosed(itemType));
  }

  @Override
  protected void execute(Component parentComponent) {
    parentComponent.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    InputStream stream = null;
    try {
      IRepositoryFileChooser fileChooser = new RepositoryFileChooser(anathemaModel.getRepository()
          .getPrintNameFileAccess(), anathemaModel.getItemManagement(), propertiesExtensionPoint);
      // todo: unterschiedliche FileChooser für Single und Multi...
      final IRepositoryReadAccess readAccess = anathemaModel.getRepository().openReadAccess(itemType, fileChooser);
      final String itemTypeDisplay = resources.getString("ItemType." + itemType.getId() + ".PrintName"); //$NON-NLS-1$ //$NON-NLS-2$
      String title = resources.getString(
          "AnathemaPersistence.LoadAction.Progress.Title", new Object[] { itemTypeDisplay }); //$NON-NLS-1$
      new ProgressMonitorDialog(parentComponent, title).run(false, new IRunnableWithProgress() {
        public void run(IProgressMonitor monitor) throws InterruptedException, InvocationTargetException {
          try {
            monitor.beginTask(resources.getString("AnathemaPersistence.LoadAction.Progress.Model", //$NON-NLS-1$
                new Object[] { itemTypeDisplay }), IProgressMonitor.UNKNOWN);
            IItem item = anathemaModel.getPersisterRegistry().get(itemType).load(readAccess);
            if (item == null) {
              return;
            }
            monitor.beginTask(resources.getString("AnathemaPersistence.LoadAction.Progress.View", //$NON-NLS-1$
                new Object[] { item.getDisplayName() }), IProgressMonitor.UNKNOWN);
            anathemaModel.getItemManagement().addItem(item);
          }
          catch (AnathemaException e) {
            throw new InvocationTargetException(e);
          }
        }
      });
    }
    catch (InvocationTargetException e) {
      String message = resources.getString("AnathemaPersistence.LoadAction.Message.Error"); //$NON-NLS-1$
      MessageUtilities.indicateMessage(getClass(), parentComponent, new Message(message, e.getCause()));
    }
    catch (Throwable e) {
      String message = resources.getString("AnathemaPersistence.LoadAction.Message.Error"); //$NON-NLS-1$
      MessageUtilities.indicateMessage(getClass(), parentComponent, new Message(message, e));
    }
    finally {
      IOUtilities.close(stream);
      parentComponent.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
  }

  private static IItemType[] collectItemTypes(IAnathemaModel model) {
    List<IItemType> types = new ArrayList<IItemType>();
    for (IItemType type : model.getItemTypeRegistry().getAllItemTypes()) {
      if (type.supportsRepository()) {
        types.add(type);
      }
    }
    return types.toArray(new IItemType[types.size()]);
  }
}