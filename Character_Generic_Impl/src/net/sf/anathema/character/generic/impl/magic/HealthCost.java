package net.sf.anathema.character.generic.impl.magic;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.magic.general.IHealthCost;

public class HealthCost extends Cost implements IHealthCost {

  public static final IHealthCost NULL_HEALTH_COST = new HealthCost(0, "", false, HealthType.Lethal); //$NON-NLS-1$
  private final HealthType type;

  public HealthCost(int cost, String text, boolean permanent, HealthType type) {
    super(String.valueOf(cost), text, permanent);
    Ensure.ensureNotNull(type);
    this.type = type;
  }

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