package net.sf.anathema.character.generic.equipment;

import net.sf.anathema.character.generic.health.HealthType;

public interface IWeaponStatistics {

  public int getSpeed();

  public int getAccuracy();

  public int getDamage();

  public HealthType getDamageType();

  public Integer getDefense();

  public Integer getRate();
}