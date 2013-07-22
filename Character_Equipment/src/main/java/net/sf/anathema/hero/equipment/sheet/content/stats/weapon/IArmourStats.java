package net.sf.anathema.hero.equipment.sheet.content.stats.weapon;

import net.sf.anathema.hero.health.HealthType;

public interface IArmourStats extends IEquipmentStats {

  Integer getFatigue();

  Integer getMobilityPenalty();

  Integer getHardness(HealthType type);

  Integer getSoak(HealthType type);
}