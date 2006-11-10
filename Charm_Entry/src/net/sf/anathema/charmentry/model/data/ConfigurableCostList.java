package net.sf.anathema.charmentry.model.data;

public class ConfigurableCostList implements IConfigurableCostList {

  private final IConfigurableCost essenceCost = new ConfigurableCost();
  private final IConfigurableCost willpowerCost = new ConfigurableCost();
  private final IConfigurableHealthCost healthCost = new ConfigurableHealthCost();

  public IConfigurableCost getEssenceCost() {
    return essenceCost;
  }

  public IConfigurableCost getWillpowerCost() {
    return willpowerCost;
  }

  public IConfigurableHealthCost getHealthCost() {
    return healthCost;
  }
}