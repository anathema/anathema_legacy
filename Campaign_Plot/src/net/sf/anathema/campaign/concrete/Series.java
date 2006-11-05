package net.sf.anathema.campaign.concrete;

import net.sf.anathema.campaign.concrete.plot.PlotModel;
import net.sf.anathema.campaign.model.ISeries;
import net.sf.anathema.campaign.model.ISeriesContentModel;
import net.sf.anathema.campaign.model.plot.IPlotModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.itemdata.model.IItemData;
import net.sf.anathema.framework.presenter.itemmanagement.PrintNameAdjuster;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public class Series implements ISeries, IItemData {

  private final ISeriesContentModel contentModel;
  private final IPlotModel plotModel = new PlotModel();

  public Series(IItemType[] contentTypes) {
    contentModel = new SeriesContentModel(contentTypes);
  }

  public ISeriesContentModel getContentModel() {
    return contentModel;
  }

  public IPlotModel getPlot() {
    return plotModel;
  }

  public void setPrintNameAdjuster(PrintNameAdjuster adjuster) {
    ITextualDescription rootName = plotModel.getRootElement().getDescription().getName();
    rootName.addTextChangedListener(adjuster);
  }

  public void addDirtyListener(IChangeListener changeListener) {
    // nothing to do
  }

  public boolean isDirty() {
    return true;
  }

  public void setClean() {
    // nothing to do
  }

  public void removeDirtyListener(IChangeListener changeListener) {
    // nothing to do
  }
}