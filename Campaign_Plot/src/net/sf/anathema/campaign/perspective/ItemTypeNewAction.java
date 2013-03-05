package net.sf.anathema.campaign.perspective;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.message.MessageUtilities;
import net.sf.anathema.framework.presenter.action.ItemCreationOperator;
import net.sf.anathema.framework.presenter.action.NewItemCreator;
import net.sf.anathema.framework.presenter.item.ItemTypeCreationViewPropertiesExtensionPoint;
import net.sf.anathema.framework.presenter.resources.PlatformUI;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.gui.wizard.IAnathemaWizardPage;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.wizard.selection.IAnathemaWizardModelTemplate;
import net.sf.anathema.lib.workflow.wizard.selection.IWizardFactory;

import javax.swing.Action;
import javax.swing.Icon;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

public class ItemTypeNewAction extends AbstractItemAction {

  private final IItemType type;
  private final ItemCreationOperator itemCreationOperator;

  public static Action[] createToolActions(IApplicationModel model, IResources resources) {
    List<Action> actions = new ArrayList<>();
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

  public ItemTypeNewAction(IItemType type, IApplicationModel model, IResources resources) {
    super(model, resources);
    this.itemCreationOperator = new ItemCreationOperator(new NewItemCreator(model), resources, model.getItemManagement(), model);
    this.type = type;
  }

  @Override
  protected void execute(Component parentComponent) {
    IRegistry<String, IAnathemaExtension> registry = getAnathemaModel().getExtensionPointRegistry();
    ItemTypeCreationViewPropertiesExtensionPoint extension =
            (ItemTypeCreationViewPropertiesExtensionPoint) registry.get(ItemTypeCreationViewPropertiesExtensionPoint.ID);
    IWizardFactory factory = extension.get(type).getNewItemWizardFactory();
    IAnathemaWizardModelTemplate template = factory.createTemplate();
    if (factory.needsFurtherDetails()) {
      IAnathemaWizardPage startPage = factory.createPage(template);
      boolean canceled = showDialog(parentComponent, startPage);
      if (canceled) {
        return;
      }
    }
    try {
      itemCreationOperator.operate(parentComponent, type, template);
    } catch (PersistenceException e) {
      Message message = new Message(getResources().getString("AnathemaPersistence.NewMenu.Message.Error"), e); //$NON-NLS-1$
      MessageUtilities.indicateMessage(ItemTypeNewAction.class, parentComponent, message);
    }
  }
}