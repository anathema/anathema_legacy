package net.sf.anathema.character.equipment.dummy;

import net.sf.anathema.character.equipment.impl.character.model.stats.AbstractCombatStats;
import net.sf.anathema.character.equipment.impl.creation.model.WeaponTag;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.lib.util.Identified;

public class DemoMeleeWeapon extends AbstractCombatStats implements IWeaponStats {

  private final int accuracy;
  private final int damage;
  private final int minimumDamage;
  private final HealthType healthType;
  private final Integer defense;
  private final int rate;
  private final int speed;
  private final int mobility;
  private final WeaponTag[] tags;
  private Identified name;

  public DemoMeleeWeapon(
      Identified name,
      int speed,
      int accuracy,
      int damage,
      int minimumDamage,
      HealthType healthType,
      int defense,
      int mobility,
      int rate,
      WeaponTag... tags) {
    this.name = name;
    this.accuracy = accuracy;
    this.damage = damage;
    this.minimumDamage = minimumDamage;
    this.healthType = healthType;
    this.defense = defense;
    this.mobility = mobility;
    this.rate = rate;
    this.speed = speed;
    this.tags = tags;
  }

  @Override
  public int getAccuracy() {
    return accuracy;
  }

  @Override
  public int getDamage() {
    return damage;
  }

  @Override
  public HealthType getDamageType() {
    return healthType;
  }
  
  @Override
  public int getMinimumDamage() {
	return minimumDamage;
  }

  @Override
  public Integer getDefence() {
    return defense;
  }
  
  @Override
  public int getMobilityPenalty() {
	return mobility;
  }

  @Override
  public Integer getRange() {
    return null;
  }

  @Override
  public Integer getRate() {
    return rate;
  }

  @Override
  public int getSpeed() {
    return speed;
  }

  @Override
  public Identified[] getTags() {
    return tags;
  }

  @Override
  public ITraitType getTraitType() {
    return AbilityType.Melee;
  }

  @Override
  public ITraitType getDamageTraitType() {
    return AttributeType.Strength;
  }

  @Override
  public boolean inflictsNoDamage() {
    return false;
  }

  @Override
  public Identified getName() {
    return name;
  }

  @Override
  public boolean isRangedCombat() {
    return false;
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