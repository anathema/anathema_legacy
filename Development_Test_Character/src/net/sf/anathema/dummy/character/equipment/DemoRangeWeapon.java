package net.sf.anathema.dummy.character.equipment;

import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class DemoRangeWeapon implements IWeaponStats {

  private final int speed;
  private final int accuracy;
  private final int damage;
  private final HealthType damageType;
  private final int range;
  private final IIdentificate name;
  private final int rate;
  private final boolean isNoDamage;

  public DemoRangeWeapon() {
    this(new Identificate("Range"), 0, 12, 15, HealthType.Bashing, 200, 3, true); //$NON-NLS-1$
  }

  public DemoRangeWeapon(
      Identificate name,
      int speed,
      int accuracy,
      int damage,
      HealthType damageType,
      int range,
      int rate,
      boolean isNoDamage) {
    this.name = name;
    this.speed = speed;
    this.accuracy = accuracy;
    this.damage = damage;
    this.damageType = damageType;
    this.range = range;
    this.rate = rate;
    this.isNoDamage = isNoDamage;

  }

  public int getAccuracy() {
    return accuracy;
  }

  public int getDamage() {
    return damage;
  }

  public HealthType getDamageType() {
    return damageType;
  }

  public Integer getDefence() {
    return null;
  }

  public Integer getRange() {
    return range;
  }

  public Integer getRate() {
    return rate;
  }

  public int getSpeed() {
    return speed;
  }

  public IIdentificate[] getTags() {
    return new IIdentificate[0];
  }

  public ITraitType getTraitType() {
    return AbilityType.MartialArts;
  }

  public ITraitType getDamageTraitType() {
    return null;
  }

  public boolean inflictsNoDamage() {
    return isNoDamage;
  }

  public IIdentificate getName() {
    return name;
  }

  public boolean isRangedCombat() {
    return true;
  }

  @Override
  public IEquipmentStats[] getViews() {
    return new IEquipmentStats[] { this };
  }

  @Override
  public String getId() {
    return getName().getId();
  }
}