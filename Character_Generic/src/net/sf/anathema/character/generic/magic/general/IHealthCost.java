package net.sf.anathema.character.generic.magic.general;

import net.sf.anathema.character.generic.health.HealthType;

public interface IHealthCost extends ICost {
  public HealthType getType();
}