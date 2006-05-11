package net.sf.anathema.charmentry.model.data;

import net.sf.anathema.character.generic.magic.general.ICostList;

public interface IConfigurableCostList extends ICostList {

  public IConfigurableCost getEssenceCost();

  public IConfigurableCost getWillpowerCost();

  public IConfigurableHealthCost getHealthCost();
}