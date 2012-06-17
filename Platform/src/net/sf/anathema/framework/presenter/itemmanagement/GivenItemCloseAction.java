package net.sf.anathema.framework.presenter.itemmanagement;

import net.sf.anathema.framework.presenter.IItemManagementModel;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.Action;

public class GivenItemCloseAction extends AbstractCloseAction {

  private final IItem item;

  public static Action createForItem(IItemManagementModel model, IResources resources, IItem item) {
    SmartAction action = new GivenItemCloseAction(model, item, resources);
    action.setIcon(new BasicUi(resources).getClearIcon());
    return action;
  }

  private GivenItemCloseAction(IItemManagementModel management, IItem item, IResources resources) {
    super(management, resources);
    this.item = item;
  }

  @Override
  protected IItem getItemToClose() {
    return item;
  }
}
