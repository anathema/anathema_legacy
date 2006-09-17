package net.sf.anathema.campaign.view;

import javax.swing.Icon;
import javax.swing.JComponent;

import net.sf.anathema.campaign.presenter.view.ISeriesView;
import net.sf.anathema.campaign.presenter.view.plot.IPlotView;
import net.sf.anathema.campaign.view.plot.PlotView;
import net.sf.anathema.framework.view.item.AbstractTabbedItemView;

public class CampaignView extends AbstractTabbedItemView implements ISeriesView {

  public CampaignView(String name, Icon icon) {
    super(name, icon);
  }

  @Override
  protected JComponent[] getTabAreaComponents() {
    return new JComponent[0];
  }

  public IPlotView addPlotView(String title) {
    IPlotView plotView = new PlotView();
    addTab(plotView, title);
    return plotView;
  }
}