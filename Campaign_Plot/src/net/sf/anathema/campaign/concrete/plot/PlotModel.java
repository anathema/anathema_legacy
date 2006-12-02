package net.sf.anathema.campaign.concrete.plot;

import net.sf.anathema.campaign.model.plot.IPlotElement;
import net.sf.anathema.campaign.model.plot.IPlotModel;
import net.sf.anathema.framework.repository.IChangeManagement;
import net.sf.anathema.lib.control.change.IChangeListener;

public class PlotModel implements IPlotModel, IChangeManagement {

  public static final String ID_SERIES = "Series"; //$NON-NLS-1$
  public static final String ID_STORY = "Story"; //$NON-NLS-1$
  public static final String ID_SCENE = "Scene"; //$NON-NLS-1$
  public static final String ID_EPISODE = "Episode"; //$NON-NLS-1$
  private PlotElement root;
  private final PlotIDProvider provider;
  private final PlotTimeUnit rootUnit;
  private final SeriesChangeManagement seriesChangeManagement;

  public PlotModel() {
    PlotTimeUnit episode = new PlotTimeUnit(ID_EPISODE, new PlotTimeUnit(ID_SCENE));
    rootUnit = new PlotTimeUnit(ID_SERIES, new PlotTimeUnit(ID_STORY, episode));
    provider = new PlotIDProvider(rootUnit);
    this.root = new PlotElement(provider, rootUnit, ID_SERIES);
    seriesChangeManagement = new SeriesChangeManagement(root);
  }

  public IPlotElement getRootElement() {
    return root;
  }

  public IPlotElement getParentElement(IPlotElement element) {
    if (element == root) {
      return null;
    }
    return searchInChildren(root, element);
  }

  private IPlotElement searchInChildren(IPlotElement parent, IPlotElement element) {
    if (!parent.getTimeUnit().hasSuccessor()) {
      return null;
    }
    if (parent.contains(element)) {
      return parent;
    }
    for (IPlotElement child : parent.getChildren()) {
      IPlotElement foundParent = searchInChildren(child, element);
      if (foundParent != null) {
        return foundParent;
      }
    }
    return null;
  }

  public boolean isDirty() {
    return seriesChangeManagement.isDirty();
  }

  public void setClean() {
    seriesChangeManagement.setClean();
  }

  public void addDirtyListener(IChangeListener changeListener) {
    seriesChangeManagement.addDirtyListener(changeListener);
  }

  public void removeDirtyListener(IChangeListener changeListener) {
    seriesChangeManagement.removeDirtyListener(changeListener);
  }
}