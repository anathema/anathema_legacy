package net.sf.anathema.charmentry.model.data;

import net.sf.anathema.character.generic.magic.general.ICostList;

public interface IConfigurableCostList extends ICostList {

  @Override
  public IConfigurableCost getEssenceCost();

  @Override
  public IConfigurableCost getWillpowerCost();

  @Override
  public IConfigurableHealthCost getHealthCost();
  
  @Override
  public IConfigurableCost getXPCost();
}