package net.sf.anathema.campaign.model.plot;

public interface IPlotModel {

  public IPlotElement getRootElement();

  public IPlotElement getParentElement(IPlotElement element);
}