package net.sf.anathema.campaign.model.plot;

public interface IPlotModel {

  public IPlotElement getRootElement();

  public IPlotElement getParentElement(IPlotElement element);

  public boolean isDirty();

  public void setClean();
}