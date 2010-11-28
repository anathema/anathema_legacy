package net.sf.anathema.campaign.model;

import net.sf.anathema.campaign.model.plot.IPlotModel;
import net.sf.anathema.framework.itemdata.model.IItemData;

public interface ISeries extends IItemData {

  public IPlotModel getPlot();
}