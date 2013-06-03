package net.sf.anathema.campaign.perspective;

import net.sf.anathema.campaign.module.PlotViewListening;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.lib.resources.Resources;

public class CampaignPerspectivePresenter {
  private final IApplicationModel model;
  private final CampaignPerspectiveView view;
  private final Resources resources;

  public CampaignPerspectivePresenter(IApplicationModel model, CampaignPerspectiveView view, Resources resources) {
    this.model = model;
    this.view = view;
    this.resources = resources;
  }

  public void initPresentation() throws InitializationException {
    new PlotViewListening().init(resources, model, view);
    new CampaignPerspectiveTool().add(resources, model, view.getToolBar());
  }
}