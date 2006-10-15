package net.sf.anathema.character.generic.equipment.weapon;

import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.util.IIdentificate;

public interface IWeaponStats extends IEquipmentStats {

  public int getAccuracy();

  public int getDamage();

  public HealthType getDamageType();

  public int getSpeed();

  public IIdentificate[] getTags();

  public ITraitType getTraitType();

  public Integer getDefence();

  public Integer getRange();

  public Integer getRate();

  public ITraitType getDamageTraitType();

  public boolean inflictsNoDamage();
  
  public boolean isRangedCombat();
}