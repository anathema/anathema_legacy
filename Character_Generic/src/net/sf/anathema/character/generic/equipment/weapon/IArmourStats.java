package net.sf.anathema.character.generic.equipment.weapon;

import net.sf.anathema.character.generic.health.HealthType;

public interface IArmourStats extends IDefensiveStats {

  public Integer getHardness(HealthType type);

  public Integer getSoak(HealthType type);
}