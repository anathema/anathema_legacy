package net.sf.anathema.campaign.model.plot;

public interface IPlotModel {

  IPlotElement getRootElement();

  IPlotElement getParentElement(IPlotElement element);

  boolean isDirty();

  void setClean();
}