package net.sf.anathema.campaign.toolbar;

import net.sf.anathema.campaign.item.IItemManagementModel;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.Action;

public class GivenCampaignCloseAction extends AbstractCloseAction {

  private final IItem item;

  public static Action createForItem(IItemManagementModel model, Resources resources, IItem item) {
    SmartAction action = new GivenCampaignCloseAction(model, item, resources);
    action.setIcon(new BasicUi().getClearIcon());
    return action;
  }

  private GivenCampaignCloseAction(IItemManagementModel management, IItem item, Resources resources) {
    super(management, resources);
    this.item = item;
  }

  @Override
  protected IItem getItemToClose() {
    return item;
  }
}
