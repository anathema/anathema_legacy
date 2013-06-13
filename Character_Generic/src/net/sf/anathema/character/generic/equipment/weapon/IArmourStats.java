package net.sf.anathema.character.generic.equipment.weapon;

import net.sf.anathema.character.generic.health.HealthType;

public interface IArmourStats extends IEquipmentStats {

  Integer getFatigue();

  Integer getMobilityPenalty();

  Integer getHardness(HealthType type);

  Integer getSoak(HealthType type);
}