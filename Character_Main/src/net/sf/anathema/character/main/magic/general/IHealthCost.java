package net.sf.anathema.character.main.magic.general;

import net.sf.anathema.character.main.health.HealthType;

public interface IHealthCost extends ICost {
  HealthType getType();
}