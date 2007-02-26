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
import net.sf.anathema.framework.presenter.IItemMangementModel;
import net.sf.anathema.framework.presenter.item.ItemTypeCreationViewPropertiesExtensionPoint;
import net.sf.anathema.framework.presenter.resources.PlatformUI;
import net.sf.anathema.framework.presenter.view.IItemTypeViewProperties;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.gui.wizard.IAnathemaWizardPage;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.wizard.selection.IAnathemaWizardModelTemplate;

public final class ItemTypeLoadAction extends AbstractItemAction {

  private final IItemType itemType;
  private final ItemCreationOperator itemCreationOperator;

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
    IItemMangementModel itemManagementModel = anathemaModel.getItemManagement();
    this.itemCreationOperator = new ItemCreationOperator(
        new LoadItemCreator(anathemaModel),
        resources,
        itemManagementModel);
    this.itemType = itemType;
    new LoadActionEnabler(anathemaModel.getRepository(), itemManagementModel, this, itemType).init();
  }

  @Override
  protected void execute(Component parentComponent) {
    ItemTypeCreationViewPropertiesExtensionPoint extension = (ItemTypeCreationViewPropertiesExtensionPoint) getAnathemaModel().getExtensionPointRegistry()
        .get(ItemTypeCreationViewPropertiesExtensionPoint.ID);
    IItemTypeViewProperties properties = extension.get(itemType);
    ItemSelectionWizardPageFactory factory = new ItemSelectionWizardPageFactory(
        itemType,
        getAnathemaModel().getRepository().getPrintNameFileAccess(),
        new LoadItemWizardProperties(getResources(), properties.getItemTypeUI()));
    IAnathemaWizardModelTemplate template = factory.createTemplate();
    IAnathemaWizardPage startPage = factory.createPage(template);
    boolean canceled = showDialog(parentComponent, startPage);
    if (canceled) {
      return;
    }
    try {
      itemCreationOperator.operate(parentComponent, itemType, template);
    }
    catch (PersistenceException e) {
      Message message = new Message(getResources().getString("AnathemaPersistence.NewMenu.Message.Error"), e); //$NON-NLS-1$
      MessageUtilities.indicateMessage(AnathemaNewAction.class, parentComponent, message);
    }
  }
}