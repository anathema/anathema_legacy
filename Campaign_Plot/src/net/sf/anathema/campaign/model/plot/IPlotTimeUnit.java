package net.sf.anathema.campaign.model.plot;

import net.sf.anathema.lib.util.IIdentificate;

// Story, Episode, Scene
public interface IPlotTimeUnit extends IIdentificate {

  public String getId();

  public IPlotTimeUnit getSuccessor();

  public boolean hasSuccessor();
}