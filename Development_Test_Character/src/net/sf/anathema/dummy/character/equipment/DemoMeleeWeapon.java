package net.sf.anathema.dummy.character.equipment;

import net.sf.anathema.character.equipment.impl.creation.model.WeaponTag;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class DemoMeleeWeapon implements IWeaponStats {

  private final int accuracy;
  private final int damage;
  private final HealthType healthType;
  private final Integer defense;
  private final int rate;
  private final int speed;
  private final WeaponTag[] tags;
  private IIdentificate name;

  public DemoMeleeWeapon() {
    this(
        new Identificate("Melee"), //$NON-NLS-1$
        2,
        -5,
        3,
        HealthType.Aggravated,
        -1,
        6,
        WeaponTag.ClinchEnhancer,
        WeaponTag.Piercing);
  }

  public DemoMeleeWeapon(
      IIdentificate name,
      int speed,
      int accuracy,
      int damage,
      HealthType healthType,
      int defense,
      int rate,
      WeaponTag... tags) {
    this.name = name;
    this.accuracy = accuracy;
    this.damage = damage;
    this.healthType = healthType;
    this.defense = defense;
    this.rate = rate;
    this.speed = speed;
    this.tags = tags;
  }

  public int getAccuracy() {
    return accuracy;
  }

  public int getDamage() {
    return damage;
  }

  public HealthType getDamageType() {
    return healthType;
  }

  public Integer getDefence() {
    return defense;
  }

  public Integer getRange() {
    return null;
  }

  public Integer getRate() {
    return rate;
  }

  public int getSpeed() {
    return speed;
  }

  public IIdentificate[] getTags() {
    return tags;
  }

  public ITraitType getTraitType() {
    return AbilityType.Melee;
  }

  public ITraitType getDamageTraitType() {
    return AttributeType.Strength;
  }

  public boolean inflictsNoDamage() {
    return false;
  }

  public IIdentificate getName() {
    return name;
  }
  
  public boolean isRangedCombat() {
    return false;
  }
}