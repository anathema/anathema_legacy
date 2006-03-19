package net.sf.anathema.campaign.concrete.plot;

import net.sf.anathema.campaign.model.plot.IPlotTimeUnit;
import net.sf.anathema.lib.util.Identificate;

public class PlotTimeUnit extends Identificate implements IPlotTimeUnit {

  private final IPlotTimeUnit successor;

  public PlotTimeUnit(String name) {
    this(name, null);
  }

  public PlotTimeUnit(String name, IPlotTimeUnit successor) {
    super(name);
    this.successor = successor;
  }

  public IPlotTimeUnit getSuccessor() {
    return successor;
  }

  public boolean hasSuccessor() {
    return successor != null;
  }
}