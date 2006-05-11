package net.sf.anathema.charmentry.model.data;

import net.sf.anathema.character.generic.magic.general.IPermanentCostList;

public interface IConfigurablePermanentCostList extends IConfigurableCostList, IPermanentCostList {

  public IConfigurableCost getXPCost();
}