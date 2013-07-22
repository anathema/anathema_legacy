package net.sf.anathema.character.main.magic.basic.cost;

public class CostList implements ICostList {

  private final Cost essence;
  private final Cost willpower;
  private final IHealthCost health;
  private final Cost xp;

  public CostList(Cost essence, Cost willpower, IHealthCost health, Cost xp) {
    this.xp = xp;
    this.essence = essence;
    this.willpower = willpower;
    this.health = health;
  }

  @Override
  public Cost getXPCost() {
    return xp != null ? xp : CostImpl.NULL_COST;
  }

  @Override
  public Cost getEssenceCost() {
    return essence != null ? essence : CostImpl.NULL_COST;
  }

  @Override
  public IHealthCost getHealthCost() {
    return health != null ? health : HealthCost.NULL_HEALTH_COST;
  }

  @Override
  public Cost getWillpowerCost() {
    return willpower != null ? willpower : CostImpl.NULL_COST;
  }
}