package net.sf.anathema.campaign.view;

import javax.swing.Icon;
import javax.swing.JComponent;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.campaign.presenter.view.ISeriesView;
import net.sf.anathema.campaign.presenter.view.plot.IPlotView;
import net.sf.anathema.campaign.view.plot.PlotView;
import net.sf.anathema.framework.view.item.AbstractItemView;

public class CampaignView extends AbstractItemView implements ISeriesView {

  private IPlotView plotView;

  public CampaignView(String name, Icon icon) {
    super(name, icon);
  }

  public IPlotView addPlotView(String title) {
    Ensure.ensureNull("Only one view allowed.", plotView); //$NON-NLS-1$
    this.plotView = new PlotView();
    return plotView;
  }

  public JComponent getComponent() {
    return plotView.getComponent();
  }
}