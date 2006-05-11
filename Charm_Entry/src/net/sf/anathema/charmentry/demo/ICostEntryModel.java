package net.sf.anathema.charmentry.demo;

import net.sf.anathema.character.generic.rules.IExaltedEdition;

public interface ICostEntryModel {

  public void setEssenceCostValue(String newValue);

  public void setEssenceCostText(String newValue);

  public void setWillpowerCostValue(String newValue);

  public void setWillpowerCostText(String newValue);

  public void setHealthCostValue(String newValue);

  public void setHealthCostText(String newValue);

  public void setXpCostValue(String newValue);

  public void setXpCostText(String newValue);

  public IExaltedEdition getEdition();

}
