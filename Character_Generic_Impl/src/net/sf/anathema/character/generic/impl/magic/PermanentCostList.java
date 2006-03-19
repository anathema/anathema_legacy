package net.sf.anathema.character.generic.impl.magic;

import net.sf.anathema.character.generic.magic.general.ICost;
import net.sf.anathema.character.generic.magic.general.ICostList;
import net.sf.anathema.character.generic.magic.general.IHealthCost;
import net.sf.anathema.character.generic.magic.general.IPermanentCostList;

public class PermanentCostList extends CostList implements IPermanentCostList {

  private final ICost xp;

  public PermanentCostList(ICost essence, ICost willpower, IHealthCost health, ICost xp) {
    super(essence, willpower, health);
    this.xp = xp;
  }

  public PermanentCostList(ICostList list, ICost xpCost) {
    this(list.getEssenceCost(), list.getWillpowerCost(), list.getHealthCost(), xpCost);
  }

  public ICost getXPCost() {
    return xp != null ? xp : Cost.NULL_COST;
  }
}