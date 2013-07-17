package net.sf.anathema.character.main.magic.basic.cost;

import net.sf.anathema.hero.health.HealthType;

public interface IHealthCost extends Cost {
  HealthType getType();
}