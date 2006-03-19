package net.sf.anathema.campaign.model;

import net.sf.anathema.campaign.model.plot.IPlotModel;
import net.sf.anathema.framework.itemdata.IItemData;

public interface ISeries extends IItemData {

  public ISeriesContentModel getContentModel();

  public IPlotModel getPlot();
}