package net.sf.anathema.test.campaign.concrete.plot;

import net.sf.anathema.campaign.model.plot.IPlotElement;
import net.sf.anathema.campaign.model.plot.IPlotElementContainer;
import net.sf.anathema.campaign.model.plot.IPlotElementContainerListener;


public class DummyPlotElementContainerListener implements IPlotElementContainerListener {

  public IPlotElementContainer addContainer;
  public IPlotElement addedChild;
  public IPlotElement removedChild;

  public void childRemoved(IPlotElement removal) {
    removedChild = removal;
  }

  public void childAdded(IPlotElementContainer container, IPlotElement newChild) {
    addContainer = container;
    addedChild = newChild;
  }

  public void childMoved(IPlotElement element, int newIndex) {
    //Nothing to do
  }

  public void childInserted(IPlotElement insertion, IPlotElementContainer parentContainer, int index) {
    //Nothing to do
  }
}