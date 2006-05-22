package net.sf.anathema.character.generic.equipment.weapon;

import net.sf.anathema.character.generic.health.HealthType;

public interface IArmour extends IEquipment {

  public Integer getFatigue();

  public Integer getHardness(HealthType type);

  public Integer getMobilityPenalty();

  public Integer getSoak(HealthType type);
}