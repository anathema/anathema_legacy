package net.sf.anathema.campaign.concrete.plot;

import net.sf.anathema.campaign.model.plot.IPlotElement;
import net.sf.anathema.campaign.model.plot.IPlotElementContainer;
import net.sf.anathema.campaign.model.plot.IPlotElementContainerListener;
import net.sf.anathema.framework.repository.IChangeManagement;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public class SeriesChangeManagement implements IChangeManagement {

  private final class IPlotElementContainerListenerImplementation implements IPlotElementContainerListener {
    private final IChangeListener changeListener;

    public IPlotElementContainerListenerImplementation(IChangeListener listener) {
      this.changeListener = listener;
    }

    public void childAdded(IPlotElementContainer container, IPlotElement newChild) {
      newChild.addPlotElementContainerListener(this);
      newChild.getDescription().addDirtyListener(changeListener);
      setDirty();
    }

    public void childInserted(IPlotElement insertion, IPlotElementContainer parentContainer, int index) {
      setDirty();
    }

    public void childMoved(IPlotElement element, int newIndex) {
      setDirty();
    }

    public void childRemoved(IPlotElement removal) {
      removal.getDescription().removeDirtyListener(changeListener);
      removal.removePlotElementContainerListener(this);
      setDirty();
    }
  }

  private final IChangeListener listener = new IChangeListener() {
    public void changeOccured() {
      setDirty();
    }
  };

  private final ChangeControl control = new ChangeControl();
  private boolean dirty = false;

  public SeriesChangeManagement(IPlotElement rootElement) {
    rootElement.getDescription().addDirtyListener(listener);
    IPlotElementContainerListenerImplementation containerListener = new IPlotElementContainerListenerImplementation(
        listener);
    rootElement.addPlotElementContainerListener(containerListener);
  }

  private void setDirty() {
    this.dirty = true;
    control.fireChangedEvent();
  }

  public void addDirtyListener(IChangeListener changeListener) {
    control.addChangeListener(changeListener);
  }

  public boolean isDirty() {
    return dirty;
  }

  public void removeDirtyListener(IChangeListener changeListener) {
    control.removeChangeListener(changeListener);
  }

  public void setClean() {
    this.dirty = false;
    control.fireChangedEvent();
  }
}