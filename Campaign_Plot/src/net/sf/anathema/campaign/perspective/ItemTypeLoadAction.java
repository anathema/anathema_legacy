package net.sf.anathema.campaign.perspective;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.message.MessageUtilities;
import net.sf.anathema.framework.presenter.IItemManagementModel;
import net.sf.anathema.framework.presenter.action.ItemCreationOperator;
import net.sf.anathema.framework.presenter.item.ItemTypeCreationViewPropertiesExtensionPoint;
import net.sf.anathema.framework.presenter.resources.PlatformUI;
import net.sf.anathema.framework.presenter.view.IItemTypeViewProperties;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.dialog.userdialog.page.IDialogPage;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.wizard.selection.IDialogModelTemplate;

import javax.swing.Action;
import javax.swing.Icon;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

public class ItemTypeLoadAction extends AbstractItemAction {

  private final IItemType itemType;
  private final ItemCreationOperator itemCreationOperator;

  public static Action[] createToolActions(IApplicationModel model, IResources resources) {
    List<Action> actions = new ArrayList<>();
    for (IItemType type : collectItemTypes(model)) {
      SmartAction action = new ItemTypeLoadAction(model, type, resources);
      action.setName(resources.getString("ItemType." + type.getId() + ".PrintName"));
      actions.add(action);
    }
    return actions.toArray(new Action[actions.size()]);
  }

  public static Icon getButtonIcon() {
    return new PlatformUI().getLoadToolBarIcon();
  }

  public static String createToolTip(IResources resources) {
    return resources.getString("AnathemaPersistence.LoadMenu.Name");
  }

  private ItemTypeLoadAction(IApplicationModel anathemaModel, IItemType itemType, IResources resources) {
    super(anathemaModel, resources);
    IItemManagementModel itemManagementModel = anathemaModel.getItemManagement();
    this.itemCreationOperator = new ItemCreationOperator(new LoadItemCreator(anathemaModel), itemManagementModel);
    this.itemType = itemType;
    new LoadActionEnabler(anathemaModel.getRepository(), itemManagementModel, this, itemType).init();
  }

  @Override
  protected void execute(Component parentComponent) {
    ItemTypeCreationViewPropertiesExtensionPoint extension =
            (ItemTypeCreationViewPropertiesExtensionPoint) getAnathemaModel().getExtensionPointRegistry()
                    .get(ItemTypeCreationViewPropertiesExtensionPoint.ID);
    IItemTypeViewProperties properties = extension.get(itemType);
    ItemSelectionTemplateFactory factory = new ItemSelectionTemplateFactory(itemType, getAnathemaModel().getRepository().getPrintNameFileAccess(),
            new LoadItemWizardProperties(getResources(), properties.getItemTypeUI()));
    IDialogModelTemplate template = factory.createTemplate();
    IDialogPage startPage = factory.createPage(template);
    boolean canceled = showDialog(parentComponent, startPage);
    if (canceled) {
      return;
    }
    try {
      itemCreationOperator.operate(parentComponent, itemType, template);
    } catch (PersistenceException e) {
      Message message = new Message(getResources().getString("AnathemaPersistence.NewMenu.Message.Error"), e);
      MessageUtilities.indicateMessage(ItemTypeLoadAction.class, parentComponent, message);
    }
  }
}
