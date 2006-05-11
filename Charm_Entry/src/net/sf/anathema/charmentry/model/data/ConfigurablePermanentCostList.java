package net.sf.anathema.charmentry.model.data;

public class ConfigurablePermanentCostList extends ConfigurableCostList implements IConfigurablePermanentCostList {

  private IConfigurableCost xpCost = new ConfigurableCost();

  public IConfigurableCost getXPCost() {
    return xpCost;
  }
}