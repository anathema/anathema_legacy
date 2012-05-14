package net.sf.anathema.campaign.concrete.plot;

import com.google.common.base.Preconditions;
import net.sf.anathema.campaign.model.plot.IPlotElement;
import net.sf.anathema.campaign.model.plot.IPlotElementContainer;
import net.sf.anathema.campaign.model.plot.IPlotElementContainerListener;
import net.sf.anathema.campaign.model.plot.IPlotTimeUnit;
import net.sf.anathema.framework.itemdata.model.IItemDescription;
import net.sf.anathema.lib.util.Identificate;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.List;

public class PlotElementContainer extends Identificate implements IPlotElementContainer {

  private final IPlotTimeUnit unit;
  private final List<IPlotElement> children = new ArrayList<IPlotElement>();
  private final Announcer<IPlotElementContainerListener> listeners = Announcer.to(IPlotElementContainerListener.class);
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

  @Override
  public IPlotTimeUnit getTimeUnit() {
    return unit;
  }

  @Override
  public IPlotElement[] getChildren() {
    return children.toArray(new IPlotElement[children.size()]);
  }

  @Override
  public IPlotElement addChild(String name) {
    Preconditions.checkArgument(unit.hasSuccessor(), "Tried to add to non-successable plot element container."); //$NON-NLS-1$
    PlotElement plotElement = new PlotElement(provider, getTimeUnit().getSuccessor(), name);
    children.add(plotElement);
    fireChildAddedEvent(plotElement);
    return plotElement;
  }

  @Override
  public IPlotElement addChild(IItemDescription description, String repositoryId) {
    Preconditions.checkArgument(unit.hasSuccessor(), "Tried to add to non-successable plot element container."); //$NON-NLS-1$
    PlotElement plotElement = new PlotElement(provider, getTimeUnit().getSuccessor(), description, repositoryId);
    children.add(plotElement);
    fireChildAddedEvent(plotElement);
    return plotElement;
  }

  private void fireChildAddedEvent(final IPlotElement element) {
    listeners.announce().childAdded(PlotElementContainer.this, element);
  }

  private void fireChildMovedEvent(final IPlotElement element, final int newIndex) {
    listeners.announce().childMoved(element, newIndex);
  }

  private void fireChildRemovedEvent(final IPlotElement element) {
    listeners.announce().childRemoved(element);
  }

  private void fireChildInsertedEvent(final IPlotElement element, final int index) {
    listeners.announce().childInserted(element, PlotElementContainer.this, index);
  }

  @Override
  public void removeChild(IPlotElement element) {
    Preconditions.checkArgument(unit.hasSuccessor(), "Tried to remove from non-successable plot element container."); //$NON-NLS-1$
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

  @Override
  public void removeChildSilent(IPlotElement element) {
    if (!children.remove(element)) {
      for (IPlotElement child : children) {
        if (child.getTimeUnit().hasSuccessor()) {
          child.removeChildSilent(element);
        }
      }
    }
  }

  @Override
  public void removePlotElementContainerListener(IPlotElementContainerListener listener) {
    listeners.removeListener(listener);
  }

  @Override
  public void addPlotElementContainerListener(IPlotElementContainerListener listener) {
    listeners.addListener(listener);
  }

  @Override
  public String toString() {
    return getTimeUnit().getId();
  }

  @Override
  public void moveChildTo(IPlotElement element, int newIndex) {
    int originalIndex = children.indexOf(element);
    if (originalIndex == newIndex || originalIndex < 0 || newIndex >= children.size() || newIndex < 0) {
      return;
    }
    children.remove(originalIndex);
    children.add(newIndex, element);
    fireChildMovedEvent(element, newIndex);
  }

  @Override
  public boolean contains(IPlotElement element) {
    return children.contains(element);
  }

  @Override
  public void insertChild(IPlotElement plotElement, int index) {
    children.add(index, plotElement);
    fireChildInsertedEvent(plotElement, index);
  }
}