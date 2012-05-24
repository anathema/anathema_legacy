package net.sf.anathema.campaign.model.plot;

import net.sf.anathema.lib.util.Identified;

// Story, Episode, Scene
public interface IPlotTimeUnit extends Identified {

  @Override
  String getId();

  IPlotTimeUnit getSuccessor();

  boolean hasSuccessor();
}