package net.sf.anathema.campaign.perspective;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.module.IntegratedItemViewListening;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.lib.resources.IResources;

public class CampaignPerspectivePresenter {
  private final IAnathemaModel model;
  private final CampaignPerspectiveView view;
  private final IResources resources;

  public CampaignPerspectivePresenter(IAnathemaModel model, CampaignPerspectiveView view, IResources resources) {
    this.model = model;
    this.view = view;
    this.resources = resources;
  }

  public void initPresentation() throws InitializationException {
    new IntegratedItemViewListening().init(resources, model, view);
    new CampaignPerspectiveTool().add(resources, model, view.getToolBar());
  }
}