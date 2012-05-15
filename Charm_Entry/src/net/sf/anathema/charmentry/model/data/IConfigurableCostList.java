package net.sf.anathema.charmentry.model.data;

import net.sf.anathema.character.generic.magic.general.ICostList;

public interface IConfigurableCostList extends ICostList {

  @Override
  IConfigurableCost getEssenceCost();

  @Override
  IConfigurableCost getWillpowerCost();

  @Override
  IConfigurableHealthCost getHealthCost();
  
  @Override
  IConfigurableCost getXPCost();
}