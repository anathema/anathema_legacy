package net.sf.anathema.framework.presenter.action;

import java.awt.Component;
import java.awt.Event;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.KeyStroke;

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
import net.sf.anathema.framework.item.repository.creation.ItemTypeSelectionProperties;
import net.sf.anathema.framework.item.repository.creation.ItemTypeSelectionWizardModel;
import net.sf.anathema.framework.message.MessageUtilities;
import net.sf.anathema.framework.persistence.IRepositoryItemPersister;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.gui.wizard.AnathemaWizardDialog;
import net.sf.anathema.lib.gui.wizard.IAnathemaWizardPage;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.wizard.selection.IObjectSelectionView;
import net.sf.anathema.lib.workflow.wizard.selection.IObjectSelectionWizardModel;
import net.sf.anathema.lib.workflow.wizard.selection.IWizardFactory;
import net.sf.anathema.lib.workflow.wizard.selection.ListObjectSelectionPageView;
import net.sf.anathema.lib.workflow.wizard.selection.ObjectSelectionWizardPage;

public class AnathemaNewAction extends SmartAction {

  private final IResources resources;
  private final IAnathemaModel anathemaModel;

  public static Action createMenuAction(IAnathemaModel anathemaModel, IResources resources) {
    SmartAction action = new AnathemaNewAction(anathemaModel, resources);
    action.setName(resources.getString("AnathemaCore.Tools.New.Name") + "\u2026"); //$NON-NLS-1$ //$NON-NLS-2$       
    return action;
  }

  public AnathemaNewAction(IAnathemaModel anathemaModel, IResources resources) {
    setAcceleratorKey(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
    this.anathemaModel = anathemaModel;
    this.resources = resources;
  }

  @Override
  protected void execute(Component parentComponent) {
    try {
      IRegistry<IItemType, IWizardFactory> registry = anathemaModel.getCreationWizardFactoryRegistry();
      IObjectSelectionWizardModel<IItemType> model = new ItemTypeSelectionWizardModel(
          collectItemTypes(anathemaModel),
          registry);
      IObjectSelectionView<IItemType> view = new ListObjectSelectionPageView<IItemType>(IItemType.class);
      IAnathemaWizardPage startPage = new ObjectSelectionWizardPage<IItemType>(
          registry,
          model,
          view,
          new ItemTypeSelectionProperties(resources));
      createItemFromWizard(parentComponent, model, startPage);
    }
    catch (Exception e) {
      Message message = new Message(resources.getString("AnathemaPersistence.NewMenu.Message.Error"), e); //$NON-NLS-1$
      MessageUtilities.indicateMessage(AnathemaNewAction.class, parentComponent, message);
    }
  }

  private void createItemFromWizard(
      Component parentComponent,
      IObjectSelectionWizardModel<IItemType> model,
      IAnathemaWizardPage startPage) throws PersistenceException {
    WizardDialog dialog = new AnathemaWizardDialog(parentComponent, startPage);
    final ISwingFrameOrDialog configuredDialog = dialog.getConfiguredDialog();
    configuredDialog.setResizable(false);
    GuiUtilities.centerToParent(configuredDialog.getWindow());
    configuredDialog.show();
    if (dialog.isCanceled()) {
      return;
    }
    IItem item = createItem(model);
    createView(parentComponent, item);
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

  private IItem createItem(IObjectSelectionWizardModel<IItemType> model) throws PersistenceException {
    IRepositoryItemPersister persister = anathemaModel.getPersisterRegistry().get(model.getSelectedObject());
    return persister.createNew(model.getTemplate(model.getSelectedObject()));
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