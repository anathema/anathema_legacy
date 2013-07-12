package net.sf.anathema.character.main.magic.model.magic.cost;

import net.sf.anathema.hero.health.HealthType;

public interface IHealthCost extends ICost {
  HealthType getType();
}