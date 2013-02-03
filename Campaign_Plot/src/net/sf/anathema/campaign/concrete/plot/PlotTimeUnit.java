package net.sf.anathema.campaign.concrete.plot;

import net.sf.anathema.campaign.model.plot.IPlotTimeUnit;
import net.sf.anathema.lib.util.Identifier;

public class PlotTimeUnit extends Identifier implements IPlotTimeUnit {

  private final IPlotTimeUnit successor;

  public PlotTimeUnit(String name) {
    this(name, null);
  }

  public PlotTimeUnit(String name, IPlotTimeUnit successor) {
    super(name);
    this.successor = successor;
  }

  @Override
  public IPlotTimeUnit getSuccessor() {
    return successor;
  }

  @Override
  public boolean hasSuccessor() {
    return successor != null;
  }
}