package net.sf.anathema.framework.module;

import java.awt.Component;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.Icon;

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
import net.sf.anathema.framework.item.repository.creation.IItemCreationTemplate;
import net.sf.anathema.framework.message.MessageUtilities;
import net.sf.anathema.framework.persistence.IRepositoryItemPersister;
import net.sf.anathema.framework.presenter.IWizardFactory;
import net.sf.anathema.framework.presenter.resources.PlatformUI;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.gui.wizard.AnathemaWizardDialog;
import net.sf.anathema.lib.gui.wizard.IAnathemaWizardPage;
import net.sf.anathema.lib.resources.IResources;

public class ItemTypeNewAction extends SmartAction {

  private final IItemType type;
  private final IAnathemaModel anathemaModel;
  private final IResources resources;

  public static Action[] createToolActions(IAnathemaModel model, IResources resources) {
    List<Action> actions = new ArrayList<Action>();
    for (IItemType type : collectItemTypes(model)) {
      ItemTypeNewAction action = new ItemTypeNewAction(type, model, resources);
      action.setName(resources.getString("ItemType." + type.getId() + ".PrintName")); //$NON-NLS-1$ //$NON-NLS-2$
      actions.add(action);
    }
    return actions.toArray(new Action[actions.size()]);
  }


  public static String createToolTip(IResources resources) {
    return resources.getString("AnathemaCore.Tools.New.Name"); //$NON-NLS-1$
  }
  
  public static Icon getButtonIcon(IResources resources) {
    return new PlatformUI(resources).getNewToolBarIcon();
  }

  public ItemTypeNewAction(IItemType type, IAnathemaModel model, IResources resources) {
    this.type = type;
    this.anathemaModel = model;
    this.resources = resources;
  }

  @Override
  protected void execute(Component parentComponent) {
    IWizardFactory factory = anathemaModel.getCreationWizardFactoryRegistry().get(type);
    IItemCreationTemplate template = factory.createTemplate();
    if (factory.needsFurtherDetails()) {
      IAnathemaWizardPage startPage = factory.createPage(template);
      WizardDialog dialog = new AnathemaWizardDialog(parentComponent, startPage);
      final ISwingFrameOrDialog configuredDialog = dialog.getConfiguredDialog();
      configuredDialog.setResizable(false);
      GuiUtilities.centerToParent(configuredDialog.getWindow());
      configuredDialog.show();
      if (dialog.isCanceled()) {
        return;
      }
    }

    try {
      IItem item = createItem(template);
      createView(parentComponent, item);
    }
    catch (PersistenceException e) {
      Message message = new Message(resources.getString("AnathemaPersistence.NewMenu.Message.Error"), e); //$NON-NLS-1$
      MessageUtilities.indicateMessage(AnathemaNewAction.class, parentComponent, message);
    }
  }

  private void createView(Component parentComponent, final IItem item) {
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

  private IItem createItem(IItemCreationTemplate template) throws PersistenceException {
    IRepositoryItemPersister persister = anathemaModel.getPersisterRegistry().get(type);
    return persister.createNew(template);
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