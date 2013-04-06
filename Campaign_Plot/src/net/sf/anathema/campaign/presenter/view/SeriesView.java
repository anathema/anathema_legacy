package net.sf.anathema.campaign.presenter.view;

import net.sf.anathema.campaign.presenter.view.plot.IPlotView;
import net.sf.anathema.framework.view.SwingItemView;

public interface SeriesView extends SwingItemView {

  IPlotView addPlotView(String title);
}