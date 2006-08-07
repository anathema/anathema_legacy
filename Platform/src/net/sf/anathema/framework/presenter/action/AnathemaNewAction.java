package net.sf.anathema.framework.presenter.action;

import java.awt.Component;
import java.awt.Event;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.KeyStroke;

import net.disy.commons.core.message.Message;
import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.dialog.core.ISwingFrameOrDialog;
import net.disy.commons.swing.dialog.wizard.WizardDialog;
import net.disy.commons.swing.util.GuiUtilities;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.item.repository.creation.ItemTypeSelectionProperties;
import net.sf.anathema.framework.item.repository.creation.ItemTypeSelectionWizardModel;
import net.sf.anathema.framework.message.MessageUtilities;
import net.sf.anathema.framework.persistence.IRepositoryItemPersister;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.gui.wizard.AnathemaWizardDialog;
import net.sf.anathema.lib.gui.wizard.IAnathemaWizardPage;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.wizard.selection.IObjectSelectionView;
import net.sf.anathema.lib.workflow.wizard.selection.IObjectSelectionWizardModel;
import net.sf.anathema.lib.workflow.wizard.selection.ListObjectSelectionPageView;
import net.sf.anathema.lib.workflow.wizard.selection.ObjectSelectionWizardPage;

public class AnathemaNewAction extends AbstractNewAction {

  public static Action createMenuAction(IAnathemaModel anathemaModel, IResources resources) {
    SmartAction action = new AnathemaNewAction(anathemaModel, resources);
    action.setName(resources.getString("AnathemaCore.Tools.New.Name") + "\u2026"); //$NON-NLS-1$ //$NON-NLS-2$       
    return action;
  }

  public AnathemaNewAction(IAnathemaModel anathemaModel, IResources resources) {
    super(anathemaModel, resources);
    setAcceleratorKey(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
  }

  @Override
  protected void execute(Component parentComponent) {
    try {
      IObjectSelectionWizardModel<IItemType> model = new ItemTypeSelectionWizardModel(
          collectItemTypes(getAnathemaModel()),
          getAnathemaModel().getCreationWizardFactoryRegistry());
      IObjectSelectionView<IItemType> view = new ListObjectSelectionPageView<IItemType>(IItemType.class);
      IAnathemaWizardPage startPage = new ObjectSelectionWizardPage<IItemType>(
          getAnathemaModel().getCreationWizardFactoryRegistry(),
          model,
          view,
          new ItemTypeSelectionProperties(getResources()));
      createItemFromWizard(parentComponent, model, startPage);
    }
    catch (Exception e) {
      Message message = new Message(getResources().getString("AnathemaPersistence.NewMenu.Message.Error"), e); //$NON-NLS-1$
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

  private IItem createItem(IObjectSelectionWizardModel<IItemType> model) throws PersistenceException {
    IRepositoryItemPersister persister = getAnathemaModel().getPersisterRegistry().get(model.getSelectedObject());
    return persister.createNew(model.getTemplate(model.getSelectedObject()));
  }
}