package net.sf.anathema.campaign.model.plot;

import net.sf.anathema.lib.util.IIdentificate;

// Story, Episode, Scene
public interface IPlotTimeUnit extends IIdentificate {

  @Override
  String getId();

  IPlotTimeUnit getSuccessor();

  boolean hasSuccessor();
}