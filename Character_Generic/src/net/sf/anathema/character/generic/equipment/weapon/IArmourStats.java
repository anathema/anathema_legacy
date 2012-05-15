package net.sf.anathema.character.generic.equipment.weapon;

import net.sf.anathema.character.generic.health.HealthType;

public interface IArmourStats extends IDefensiveStats {

  Integer getHardness(HealthType type);

  Integer getSoak(HealthType type);
}