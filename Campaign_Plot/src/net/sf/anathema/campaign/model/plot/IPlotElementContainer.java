package net.sf.anathema.campaign.model.plot;

import net.sf.anathema.framework.itemdata.model.IItemDescription;
import net.sf.anathema.lib.util.IIdentificate;

public interface IPlotElementContainer extends IIdentificate{

  public IPlotElement addChild(IItemDescription description, String repositoryId);

  public IPlotElement addChild(String name);

  public void addPlotElementContainerListener(IPlotElementContainerListener listener);

  public boolean contains(IPlotElement element);

  public IPlotElement[] getChildren();

  public IPlotTimeUnit getTimeUnit();
  
  public void moveChildTo(IPlotElement element, int newIndex);

  public void removeChild(IPlotElement element);

  public void removePlotElementContainerListener(IPlotElementContainerListener modelListener);

  public void insertChild(IPlotElement element, int index);

  public void removeChildSilent(IPlotElement element);
}