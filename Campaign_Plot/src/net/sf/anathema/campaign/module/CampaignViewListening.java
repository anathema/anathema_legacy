package net.sf.anathema.campaign.module;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.presenter.IItemManagementModel;
import net.sf.anathema.framework.presenter.IModelViewMapping;
import net.sf.anathema.framework.presenter.ModelViewMapping;
import net.sf.anathema.framework.view.IItemViewManagement;
import net.sf.anathema.lib.resources.Resources;

public class CampaignViewListening {

  public void init(Resources resources, IApplicationModel model, IItemViewManagement itemViewManagement) {
    IModelViewMapping mapping = new ModelViewMapping();
    IItemManagementModel itemManagement = model.getItemManagement();
    CampaginActionFactory actionFactory = new CampaginActionFactory(itemManagement, resources);
    itemManagement.addListener(new CampaignManagementModelListener(model.getViewFactoryRegistry(), itemViewManagement, mapping, actionFactory));
    itemViewManagement.addViewSelectionListener(new CampaignViewSelectionListener(itemManagement, mapping));
  }
}
