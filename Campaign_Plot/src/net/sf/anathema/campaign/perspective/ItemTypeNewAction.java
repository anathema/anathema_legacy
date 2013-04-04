package net.sf.anathema.campaign.perspective;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.IItemManagementModel;
import net.sf.anathema.framework.presenter.action.NewItemCommand;
import net.sf.anathema.framework.presenter.resources.PlatformUI;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.Action;
import javax.swing.Icon;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

public class ItemTypeNewAction extends AbstractItemAction {

  private final IItemType type;

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
    return resources.getString("AnathemaCore.Tools.New.Name");
  }

  public static Icon getButtonIcon() {
    return new PlatformUI().getNewToolBarIcon();
  }

  public ItemTypeNewAction(IItemType type, IApplicationModel model, IResources resources) {
    super(model, resources);
    this.type = type;
  }

  @Override
  protected void execute(Component parentComponent) {
    IApplicationModel anathemaModel = getAnathemaModel();
    IItemManagementModel receiver = getAnathemaModel().getItemManagement();
    NewItemCommand command = new NewItemCommand(type, anathemaModel, getResources(), receiver);
    command.execute();
  }
}