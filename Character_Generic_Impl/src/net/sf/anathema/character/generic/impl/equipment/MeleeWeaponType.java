package net.sf.anathema.character.generic.impl.equipment;

import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.traits.types.AbilityType;

public class MeleeWeaponType {

  private final String id;
  private final AbilityType[] abilities;
  private final int speed;
  private final int accuracy;
  private final int damage;
  private final HealthType damageType;
  private final Integer defense;
  private final Integer rate;

  public MeleeWeaponType(
      String id,
      AbilityType ability,
      int speed,
      int accuracy,
      int damage,
      HealthType damageType,
      Integer defense,
      Integer rate) {
    this.id = id;
    this.abilities = (new AbilityType[] { ability });
    this.speed = speed;
    this.accuracy = accuracy;
    this.damage = damage;
    this.damageType = damageType;
    this.defense = defense;
    this.rate = rate;
  }

  public AbilityType[] getAllowedAbilities() {
    return abilities;
  }

  public int getSpeed() {
    return speed;
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

  public Integer getDefense() {
    return defense;
  }

  public Integer getRate() {
    return rate;
  }

  public String getId() {
    return id;
  }
}