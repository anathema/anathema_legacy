package net.sf.anathema.framework.presenter.itemmanagement;

import javax.swing.Action;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.framework.presenter.IItemManagementModel;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.resources.IResources;

public class AnathemaItemCloseAction extends AbstractAnathemaCloseAction {

  private static final long serialVersionUID = -5895014585326937713L;
  private final IItem item;

  public static Action createForItem(IItemManagementModel model, IResources resources, IItem item) {
    SmartAction action = new AnathemaItemCloseAction(model, item, resources);
    action.setIcon(new BasicUi(resources).getClearIcon());
    return action;
  }

  private AnathemaItemCloseAction(IItemManagementModel management, IItem item, IResources resources) {
    super(management, resources);
    this.item = item;
  }

  @Override
  protected IItem getItemToClose() {
    return item;
  }
}
