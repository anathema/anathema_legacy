package net.sf.anathema.framework.presenter.action;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.Icon;

import net.disy.commons.core.message.Message;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.message.MessageUtilities;
import net.sf.anathema.framework.presenter.item.ItemTypeCreationViewPropertiesExtensionPoint;
import net.sf.anathema.framework.presenter.resources.PlatformUI;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.gui.wizard.IAnathemaWizardPage;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.wizard.selection.IAnathemaWizardModelTemplate;
import net.sf.anathema.lib.workflow.wizard.selection.IWizardFactory;

public class ItemTypeNewAction extends AbstractItemAction {

  private final IItemType type;
  private final ItemCreationOperator itemCreationOperator;

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
    this.itemCreationOperator = new ItemCreationOperator(
        new NewItemCreator(model),
        resources,
        model.getItemManagement());
    this.type = type;
  }

  @Override
  protected void execute(Component parentComponent) {
    ItemTypeCreationViewPropertiesExtensionPoint extension = (ItemTypeCreationViewPropertiesExtensionPoint) getAnathemaModel().getExtensionPointRegistry()
        .get(ItemTypeCreationViewPropertiesExtensionPoint.ID);
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
    }
    catch (PersistenceException e) {
      Message message = new Message(getResources().getString("AnathemaPersistence.NewMenu.Message.Error"), e); //$NON-NLS-1$
      MessageUtilities.indicateMessage(AnathemaNewAction.class, parentComponent, message);
    }
  }
}