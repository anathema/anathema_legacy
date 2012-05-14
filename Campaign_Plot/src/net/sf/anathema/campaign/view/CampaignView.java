package net.sf.anathema.campaign.view;

import com.google.common.base.Preconditions;
import net.sf.anathema.campaign.presenter.view.ISeriesView;
import net.sf.anathema.campaign.presenter.view.plot.IPlotView;
import net.sf.anathema.campaign.view.plot.PlotView;
import net.sf.anathema.framework.view.item.AbstractItemView;

import javax.swing.Icon;
import javax.swing.JComponent;

public class CampaignView extends AbstractItemView implements ISeriesView {

  private IPlotView plotView;

  public CampaignView(String name, Icon icon) {
    super(name, icon);
  }

  @Override
  public IPlotView addPlotView(String title) {
    Preconditions.checkArgument(plotView == null, "Only one view allowed."); //$NON-NLS-1$
    this.plotView = new PlotView();
    return plotView;
  }

  @Override
  public JComponent getComponent() {
    return plotView.getComponent();
  }
}