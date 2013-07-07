package net.sf.anathema.character.main.equipment.weapon;

import net.sf.anathema.character.main.health.HealthType;

public interface IArmourStats extends IEquipmentStats {

  Integer getFatigue();

  Integer getMobilityPenalty();

  Integer getHardness(HealthType type);

  Integer getSoak(HealthType type);
}