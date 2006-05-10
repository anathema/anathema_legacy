package net.sf.anathema.charmentry.demo.model;

import net.sf.anathema.charmentry.demo.ICostEntryModel;
import net.sf.anathema.charmentry.model.IConfigurableCharmData;

public class CostEntryModel implements ICostEntryModel {

  private final IConfigurableCharmData charmData;

  public CostEntryModel(IConfigurableCharmData charmData) {
    this.charmData = charmData;
  }

  public void setEssenceCostValue(String newValue) {
    charmData.getTemporaryCost().getEssenceCost().setValue(newValue);
  }

  public void setEssenceCostText(String newValue) {
    charmData.getTemporaryCost().getEssenceCost().setText(newValue);
  }

  public void setWillpowerCostValue(String newValue) {
    charmData.getTemporaryCost().getWillpowerCost().setValue(newValue);
  }

  public void setWillpowerCostText(String newValue) {
    charmData.getTemporaryCost().getWillpowerCost().setText(newValue);
  }

  public void setHealthCostValue(String newValue) {
    charmData.getTemporaryCost().getHealthCost().setValue(newValue);
  }

  public void setHealthCostText(String newValue) {
    charmData.getTemporaryCost().getHealthCost().setText(newValue);
  }

  public void setXpCostValue(String newValue) {
    charmData.getPermanentCost().getXPCost().setValue(newValue);
  }

  public void setXpCostText(String newValue) {
    charmData.getPermanentCost().getXPCost().setText(newValue);
  }

}
