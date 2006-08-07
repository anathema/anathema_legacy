package net.sf.anathema.framework.presenter.action;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.Icon;

import net.disy.commons.core.message.Message;
import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.message.MessageUtilities;
import net.sf.anathema.framework.persistence.IRepositoryItemPersister;
import net.sf.anathema.framework.presenter.ItemManagementModelAdapter;
import net.sf.anathema.framework.presenter.resources.PlatformUI;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.framework.repository.access.IRepositoryReadAccess;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.gui.wizard.IAnathemaWizardPage;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.wizard.selection.IAnathemaWizardModelTemplate;

public final class ItemTypeLoadAction extends AbstractItemAction {

  private final IItemType itemType;

  public static Action[] createToolActions(IAnathemaModel model, IResources resources) {
    List<Action> actions = new ArrayList<Action>();
    for (IItemType type : collectItemTypes(model)) {
      SmartAction action = new ItemTypeLoadAction(model, type, resources);
      action.setName(resources.getString("ItemType." + type.getId() + ".PrintName")); //$NON-NLS-1$ //$NON-NLS-2$
      actions.add(action);
    }
    return actions.toArray(new Action[actions.size()]);
  }

  public static Icon getButtonIcon(IResources resources) {
    return new PlatformUI(resources).getLoadToolBarIcon();
  }

  public static String createToolTip(IResources resources) {
    return resources.getString("AnathemaPersistence.LoadMenu.Name"); //$NON-NLS-1$
  }

  private ItemTypeLoadAction(IAnathemaModel anathemaModel, IItemType itemType, IResources resources) {
    super(anathemaModel, resources);
    this.itemType = itemType;
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
    LoadItemWizardPageFactory factory = new LoadItemWizardPageFactory(itemType, getAnathemaModel().getRepository()
        .getPrintNameFileAccess(), getAnathemaModel().getItemManagement(), getResources());
    IAnathemaWizardModelTemplate template = factory.createTemplate();
    IAnathemaWizardPage startPage = factory.createPage(template);
    boolean canceled = showDialog(parentComponent, startPage);
    if (canceled) {
      return;
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
    IRepositoryItemPersister persister = getAnathemaModel().getPersisterRegistry().get(itemType);
    try {
      IRepositoryReadAccess readAccess = getAnathemaModel().getRepository().openReadAccess(
          itemType,
          (IFileProvider) template);
      return persister.load(readAccess);
    }
    catch (RepositoryException e) {
      throw new PersistenceException("An exception occured while loading.", e); //$NON-NLS-1$
    }
  }
}