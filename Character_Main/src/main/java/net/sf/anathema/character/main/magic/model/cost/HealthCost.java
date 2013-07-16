package net.sf.anathema.character.main.magic.model.cost;

import com.google.common.base.Preconditions;
import net.sf.anathema.hero.health.HealthType;

public class HealthCost extends CostImpl implements IHealthCost {

  public static final IHealthCost NULL_HEALTH_COST = new HealthCost(0, "", false, HealthType.Lethal);
  private final HealthType type;

  public HealthCost(int cost, String text, boolean permanent, HealthType type) {
    super(String.valueOf(cost), text, permanent);
    Preconditions.checkNotNull(type);
    this.type = type;
  }

  @Override
  public HealthType getType() {
    return type;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof HealthCost)) {
      return false;
    }
    HealthCost other = (HealthCost) obj;
    return super.equals(other) && other.type == this.type;
  }

  @Override
  public int hashCode() {
    return super.hashCode() + type.hashCode();
  }
}