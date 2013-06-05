package net.sf.anathema.campaign.toolbar;

import net.sf.anathema.campaign.item.PlotItemManagement;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.icon.ImageProvider;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.Action;

public class GivenCampaignCloseAction extends AbstractCloseAction {

  private final IItem item;

  public static Action createForItem(PlotItemManagement model, Resources resources, IItem item) {
    SmartAction action = new GivenCampaignCloseAction(model, item, resources);
    action.setIcon(new ImageProvider().getImageIcon(new BasicUi().getClearIconPath()));
    return action;
  }

  private GivenCampaignCloseAction(PlotItemManagement management, IItem item, Resources resources) {
    super(management, resources);
    this.item = item;
  }

  @Override
  protected IItem getItemToClose() {
    return item;
  }
}
