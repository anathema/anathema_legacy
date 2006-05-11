package net.sf.anathema.charmentry.model.data;

public class ConfigurableCostList implements IConfigurableCostList {

  private IConfigurableCost essenceCost = new ConfigurableCost();
  private IConfigurableCost willpowerCost = new ConfigurableCost();
  private IConfigurableHealthCost healthCost = new ConfigurableHealthCost();

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