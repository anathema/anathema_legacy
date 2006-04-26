package net.sf.anathema.campaign.concrete.plot;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.campaign.model.plot.IPlotElement;
import net.sf.anathema.campaign.model.plot.IPlotElementContainer;
import net.sf.anathema.campaign.model.plot.IPlotElementContainerListener;
import net.sf.anathema.campaign.model.plot.IPlotTimeUnit;
import net.sf.anathema.framework.itemdata.model.IItemDescription;
import net.sf.anathema.lib.util.Identificate;

public class PlotElementContainer extends Identificate implements IPlotElementContainer {

  private final IPlotTimeUnit unit;
  private final List<IPlotElement> children = new ArrayList<IPlotElement>();
  private final List<IPlotElementContainerListener> listeners = new ArrayList<IPlotElementContainerListener>();
  private final PlotIDProvider provider;

  public PlotElementContainer(PlotIDProvider provider, IPlotTimeUnit unit) {
    this(provider, unit, unit.getId() + provider.getIDNumber(unit));
  }

  public PlotElementContainer(PlotIDProvider provider, IPlotTimeUnit unit, String id) {
    super(id);
    this.provider = provider;
    this.unit = unit;
    this.provider.setIDNumberUsed(unit, Integer.parseInt(id.substring(unit.getId().length())));
  }

  public IPlotTimeUnit getTimeUnit() {
    return unit;
  }

  public IPlotElement[] getChildren() {
    return children.toArray(new IPlotElement[children.size()]);
  }

  public IPlotElement addChild(String name) {
    Ensure.ensureTrue("Tried to add to non-successable plot element container.", unit.hasSuccessor()); //$NON-NLS-1$
    PlotElement plotElement = new PlotElement(provider, getTimeUnit().getSuccessor(), name);
    children.add(plotElement);
    fireChildAddedEvent(plotElement);
    return plotElement;
  }

  public IPlotElement addChild(IItemDescription description, String repositoryId) {
    Ensure.ensureTrue("Tried to add to non-successable plot element container.", unit.hasSuccessor()); //$NON-NLS-1$
    PlotElement plotElement = new PlotElement(provider, getTimeUnit().getSuccessor(), description, repositoryId);
    children.add(plotElement);
    fireChildAddedEvent(plotElement);
    return plotElement;
  }

  private synchronized void fireChildAddedEvent(IPlotElement element) {
    for (IPlotElementContainerListener listener : new ArrayList<IPlotElementContainerListener>(listeners)) {
      listener.childAdded(this, element);
    }
  }

  private synchronized void fireChildMovedEvent(IPlotElement element, int newIndex) {
    for (IPlotElementContainerListener listener : new ArrayList<IPlotElementContainerListener>(listeners)) {
      listener.childMoved(element, newIndex);
    }
  }

  private synchronized void fireChildRemovedEvent(IPlotElement element) {
    for (IPlotElementContainerListener listener : new ArrayList<IPlotElementContainerListener>(listeners)) {
      listener.childRemoved(element);
    }
  }

  private synchronized void fireChildInsertedEvent(IPlotElement element, int index) {
    for (IPlotElementContainerListener listener : new ArrayList<IPlotElementContainerListener>(listeners)) {
      listener.childInserted(element, this, index);
    }
  }

  public void removeChild(IPlotElement element) {
    Ensure.ensureTrue("Tried to remove from non-successable plot element container.", unit.hasSuccessor()); //$NON-NLS-1$
    if (!children.remove(element)) {
      for (IPlotElement child : children) {
        if (child.getTimeUnit().hasSuccessor()) {
          child.removeChild(element);
        }
      }
    }
    else {
      fireChildRemovedEvent(element);
    }
  }

  public void removeChildSilent(IPlotElement element) {
    if (!children.remove(element)) {
      for (IPlotElement child : children) {
        if (child.getTimeUnit().hasSuccessor()) {
          child.removeChildSilent(element);
        }
      }
    }
  }

  public synchronized void removePlotElementContainerListener(IPlotElementContainerListener listener) {
    listeners.remove(listener);
  }

  public synchronized void addPlotElementContainerListener(IPlotElementContainerListener listener) {
    listeners.add(listener);
  }

  @Override
  public String toString() {
    return getTimeUnit().getId();
  }

  public void moveChildTo(IPlotElement element, int newIndex) {
    int originalIndex = children.indexOf(element);
    if (originalIndex == newIndex || originalIndex < 0 || newIndex >= children.size() || newIndex < 0) {
      return;
    }
    children.remove(originalIndex);
    children.add(newIndex, element);
    fireChildMovedEvent(element, newIndex);
  }

  public boolean contains(IPlotElement element) {
    return children.contains(element);
  }

  public void insertChild(IPlotElement plotElement, int index) {
    children.add(index, plotElement);
    fireChildInsertedEvent(plotElement, index);
  }
}