package net.sf.anathema.campaign.concrete.plot;

import net.sf.anathema.campaign.model.plot.IPlotElement;
import net.sf.anathema.campaign.model.plot.IPlotElementContainer;
import net.sf.anathema.campaign.model.plot.IPlotElementContainerListener;
import net.sf.anathema.framework.repository.IChangeManagement;
import net.sf.anathema.lib.control.change.IChangeListener;
import org.jmock.example.announcer.Announcer;

public class SeriesChangeManagement implements IChangeManagement {

  private final class IPlotElementContainerListenerImplementation implements IPlotElementContainerListener {
    private final IChangeListener changeListener;

    public IPlotElementContainerListenerImplementation(IChangeListener listener) {
      this.changeListener = listener;
    }

    @Override
    public void childAdded(IPlotElementContainer container, IPlotElement newChild) {
      newChild.addPlotElementContainerListener(this);
      newChild.getDescription().addDirtyListener(changeListener);
      setDirty();
    }

    @Override
    public void childInserted(IPlotElement insertion, IPlotElementContainer parentContainer, int index) {
      setDirty();
    }

    @Override
    public void childMoved(IPlotElement element, int newIndex) {
      setDirty();
    }

    @Override
    public void childRemoved(IPlotElement removal) {
      removal.getDescription().removeDirtyListener(changeListener);
      removal.removePlotElementContainerListener(this);
      setDirty();
    }
  }

  private final IChangeListener listener = new IChangeListener() {
    @Override
    public void changeOccurred() {
      setDirty();
    }
  };
  private final Announcer<IChangeListener> control = Announcer.to(IChangeListener.class);
  private boolean dirty = false;

  public SeriesChangeManagement(IPlotElement rootElement) {
    rootElement.getDescription().addDirtyListener(listener);
    IPlotElementContainerListenerImplementation containerListener = new IPlotElementContainerListenerImplementation(
        listener);
    rootElement.addPlotElementContainerListener(containerListener);
  }

  private void setDirty() {
    this.dirty = true;
    control.announce().changeOccurred();
  }

  @Override
  public void addDirtyListener(IChangeListener changeListener) {
    control.addListener(changeListener);
  }

  @Override
  public boolean isDirty() {
    return dirty;
  }

  @Override
  public void removeDirtyListener(IChangeListener changeListener) {
    control.removeListener(changeListener);
  }

  @Override
  public void setClean() {
    this.dirty = false;
    control.announce().changeOccurred();
  }
}