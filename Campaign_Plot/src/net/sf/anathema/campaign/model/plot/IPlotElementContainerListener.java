package net.sf.anathema.campaign.model.plot;

public interface IPlotElementContainerListener {

  void childRemoved(IPlotElement removal);

  void childAdded(IPlotElementContainer container, IPlotElement newChild);

  void childMoved(IPlotElement element, int newIndex);

  void childInserted(IPlotElement insertion, IPlotElementContainer parentContainer, int index);
}