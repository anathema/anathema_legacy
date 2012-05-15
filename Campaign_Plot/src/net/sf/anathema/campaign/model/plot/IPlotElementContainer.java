package net.sf.anathema.campaign.model.plot;

import net.sf.anathema.framework.itemdata.model.IItemDescription;
import net.sf.anathema.lib.util.IIdentificate;

public interface IPlotElementContainer extends IIdentificate {

  IPlotElement addChild(IItemDescription description, String repositoryId);

  IPlotElement addChild(String name);

  void addPlotElementContainerListener(IPlotElementContainerListener listener);

  boolean contains(IPlotElement element);

  IPlotElement[] getChildren();

  IPlotTimeUnit getTimeUnit();

  void moveChildTo(IPlotElement element, int newIndex);

  void removeChild(IPlotElement element);

  void removePlotElementContainerListener(IPlotElementContainerListener modelListener);

  void insertChild(IPlotElement element, int index);

  void removeChildSilent(IPlotElement element);
}