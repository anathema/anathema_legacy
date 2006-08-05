package net.sf.anathema.framework.module;

import java.awt.Component;
import java.awt.Cursor;
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
import net.sf.anathema.framework.item.repository.creation.IItemTypeSelectionView;
import net.sf.anathema.framework.item.repository.creation.INewItemWizardModel;
import net.sf.anathema.framework.item.repository.creation.ItemTypeSelectionView;
import net.sf.anathema.framework.item.repository.creation.NewItemWizardModel;
import net.sf.anathema.framework.item.repository.creation.SelectItemTypePage;
import net.sf.anathema.framework.message.MessageUtilities;
import net.sf.anathema.framework.persistence.IRepositoryItemPersister;
import net.sf.anathema.framework.presenter.IWizardFactory;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.gui.wizard.AnathemaWizardDialog;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

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
    parentComponent.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    try {
      IRegistry<IItemType, IWizardFactory> registry = anathemaModel.getCreationWizardFactoryRegistry();
      INewItemWizardModel model = new NewItemWizardModel(collectItemTypes(), registry);
      IItemTypeSelectionView view = new ItemTypeSelectionView();      
      SelectItemTypePage startPage = new SelectItemTypePage(resources, registry, model, view);
      WizardDialog dialog = new AnathemaWizardDialog(parentComponent, startPage);
      final ISwingFrameOrDialog configuredDialog = dialog.getConfiguredDialog();
      configuredDialog.setResizable(false);
      GuiUtilities.centerToParent(configuredDialog.getWindow());
      configuredDialog.show();
      if (dialog.isCanceled()) {
        return;
      }
      IItem item = createItem(model);
      create(parentComponent, item);
    }
    catch (Exception e) {
      Message message = new Message(resources.getString("AnathemaPersistence.NewMenu.Message.Error"), e); //$NON-NLS-1$
      MessageUtilities.indicateMessage(AnathemaNewAction.class, parentComponent, message);
    }
    finally {
      parentComponent.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
  }

  private void create(Component parentComponent, final IItem item) {
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

  private IItem createItem(INewItemWizardModel model) throws PersistenceException {
    IRepositoryItemPersister persister = anathemaModel.getPersisterRegistry().get(model.getSelectedItemType());
    return persister.createNew(model.getTemplate(model.getSelectedItemType()));
  }

  private IItemType[] collectItemTypes() {
    List<IItemType> types = new ArrayList<IItemType>();
    for (IItemType type : anathemaModel.getItemTypeRegistry().getAllItemTypes()) {
      if (type.supportsRepository()) {
        types.add(type);
      }
    }
    return types.toArray(new IItemType[types.size()]);
  }
}
