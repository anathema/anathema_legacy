package net.sf.anathema.campaign.concrete;

import net.sf.anathema.campaign.concrete.plot.PlotModel;
import net.sf.anathema.campaign.model.ISeries;
import net.sf.anathema.campaign.model.plot.IPlotModel;
import net.sf.anathema.framework.presenter.itemmanagement.PrintNameAdjuster;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public class Series implements ISeries {

  private final PlotModel plotModel = new PlotModel();

  @Override
  public IPlotModel getPlot() {
    return plotModel;
  }

  @Override
  public void setPrintNameAdjuster(PrintNameAdjuster adjuster) {
    ITextualDescription rootName = plotModel.getRootElement().getDescription().getName();
    rootName.addTextChangedListener(adjuster);
  }

  @Override
  public void addDirtyListener(IChangeListener changeListener) {
    plotModel.addDirtyListener(changeListener);
  }

  @Override
  public boolean isDirty() {
    return plotModel.isDirty();
  }

  @Override
  public void setClean() {
    plotModel.setClean();
  }

  @Override
  public void removeDirtyListener(IChangeListener changeListener) {
    plotModel.removeDirtyListener(changeListener);
  }
}