package net.sf.anathema.campaign.module;

import net.sf.anathema.campaign.toolbar.GivenCampaignCloseAction;
import net.sf.anathema.framework.model.IItemActionFactory;
import net.sf.anathema.campaign.item.IItemManagementModel;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.Action;

public class CampaginActionFactory implements IItemActionFactory {

  private final IItemManagementModel model;
  private final Resources resources;

  public CampaginActionFactory(IItemManagementModel model, Resources resources) {
    this.model = model;
    this.resources = resources;
  }

  @Override
  public Action createAction(IItem item) {
    return GivenCampaignCloseAction.createForItem(model, resources, item);
  }
}
