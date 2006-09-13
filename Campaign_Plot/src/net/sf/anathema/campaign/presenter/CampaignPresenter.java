package net.sf.anathema.campaign.presenter;

import net.sf.anathema.campaign.model.ISeries;
import net.sf.anathema.campaign.presenter.plot.PlotPresenter;
import net.sf.anathema.campaign.presenter.view.ISeriesView;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.resources.IResources;

public class CampaignPresenter implements IPresenter {

  private final ISeriesView campaignView;
  private final ISeries campaign;
  private final IResources resources;

  public CampaignPresenter(ISeriesView campaignView, IResources resources, ISeries campaign) {
    this.campaignView = campaignView;
    this.resources = resources;
    this.campaign = campaign;
  }

  public void initPresentation() {
    new PlotPresenter(
        resources,
        campaignView.addPlotView(resources.getString("CampaignDescription.BorderTitle")), campaign.getPlot()).initPresentation(); //$NON-NLS-1$
    // new CampaignContentPresenter(campaignView.addContentView(configuration.getResources().getString(
    // "CampaignContent.BorderTitle")), configuration, campaign, anathemaModel).initPresentation(); //$NON-NLS-1$
  }
}