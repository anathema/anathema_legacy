package net.sf.anathema.framework.presenter.action;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.Icon;

import net.disy.commons.core.message.Message;
import net.disy.commons.swing.dialog.core.ISwingFrameOrDialog;
import net.disy.commons.swing.dialog.wizard.WizardDialog;
import net.disy.commons.swing.util.GuiUtilities;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.message.MessageUtilities;
import net.sf.anathema.framework.persistence.IRepositoryItemPersister;
import net.sf.anathema.framework.presenter.resources.PlatformUI;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.gui.wizard.AnathemaWizardDialog;
import net.sf.anathema.lib.gui.wizard.IAnathemaWizardPage;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.wizard.selection.IAnathemaWizardModelTemplate;
import net.sf.anathema.lib.workflow.wizard.selection.IWizardFactory;

public class ItemTypeNewAction extends AbstractItemAction {

  private final IItemType type;

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
    super(model, resources);
    this.type = type;
  }

  @Override
  protected void execute(Component parentComponent) {
    IWizardFactory factory = getAnathemaModel().getNewItemWizardFactoryRegistry().get(type);
    IAnathemaWizardModelTemplate template = factory.createTemplate();
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
      Message message = new Message(getResources().getString("AnathemaPersistence.NewMenu.Message.Error"), e); //$NON-NLS-1$
      MessageUtilities.indicateMessage(AnathemaNewAction.class, parentComponent, message);
    }
  }

  private IItem createItem(IAnathemaWizardModelTemplate template) throws PersistenceException {
    IRepositoryItemPersister persister = getAnathemaModel().getPersisterRegistry().get(type);
    return persister.createNew(template);
  }
}