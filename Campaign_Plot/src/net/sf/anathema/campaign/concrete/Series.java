package net.sf.anathema.campaign.concrete;

import net.sf.anathema.campaign.concrete.plot.PlotModel;
import net.sf.anathema.campaign.model.ISeries;
import net.sf.anathema.campaign.model.plot.IPlotModel;
import net.sf.anathema.framework.presenter.itemmanagement.PrintNameAdjuster;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public class Series implements ISeries {

  private final PlotModel plotModel = new PlotModel();

  public IPlotModel getPlot() {
    return plotModel;
  }

  public void setPrintNameAdjuster(PrintNameAdjuster adjuster) {
    ITextualDescription rootName = plotModel.getRootElement().getDescription().getName();
    rootName.addTextChangedListener(adjuster);
  }

  public void addDirtyListener(IChangeListener changeListener) {
    plotModel.addDirtyListener(changeListener);
  }

  public boolean isDirty() {
    return plotModel.isDirty();
  }

  public void setClean() {
    plotModel.setClean();
  }

  public void removeDirtyListener(IChangeListener changeListener) {
    plotModel.removeDirtyListener(changeListener);
  }
}