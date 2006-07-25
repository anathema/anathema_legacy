package net.sf.anathema.campaign.presenter.view;

import net.sf.anathema.campaign.presenter.view.plot.IPlotView;
import net.sf.anathema.framework.view.IItemView;

public interface ISeriesView extends IItemView {

  public IPlotView addPlotView(String title);
}