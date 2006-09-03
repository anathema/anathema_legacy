package net.sf.anathema.character.equipment.impl.character.model.stats;

import java.util.List;

import net.sf.anathema.character.equipment.impl.creation.model.WeaponTag;
import net.sf.anathema.character.equipment.item.model.ICollectionFactory;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.lib.util.IIdentificate;

public class WeaponStats extends AbstractStats implements IWeaponStats {

  private int accuracy;
  private int damage;
  private HealthType damageType;
  private Integer defence;
  private Integer range;
  private Integer rate;
  private int speed;
  private boolean inflictsNoDamage;
  private final List<IIdentificate> tags;
  private final boolean isMelee;

  public WeaponStats(ICollectionFactory collectionFactory, boolean isMelee) {
    this.isMelee = isMelee;
    tags = collectionFactory.createList();
  }

  public int getAccuracy() {
    return accuracy;
  }

  public int getDamage() {
    return damage;
  }

  public ITraitType getDamageTraitType() {
    return AttributeType.Strength;
  }

  public HealthType getDamageType() {
    return damageType;
  }

  public Integer getDefence() {
    return defence;
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
    return tags.toArray(new IIdentificate[tags.size()]);
  }

  public ITraitType getTraitType() {
    return isMelee ? AbilityType.Melee : (tags.contains(WeaponTag.Thrown) ? AbilityType.Thrown : AbilityType.Archery);
  }

  public boolean inflictsNoDamage() {
    return inflictsNoDamage;
  }

  public void setAccuracy(int accuracy) {
    this.accuracy = accuracy;
  }

  public void setDamage(int damage) {
    this.damage = damage;
  }

  public void setDamageType(HealthType damageType) {
    this.damageType = damageType;
  }

  public void setDefence(Integer defence) {
    this.defence = defence;
  }

  public void setInflictsNoDamage(boolean inflictsNoDamage) {
    this.inflictsNoDamage = inflictsNoDamage;
  }

  public void setRange(Integer range) {
    this.range = range;
  }

  public void setRate(Integer rate) {
    this.rate = rate;
  }

  public void setSpeed(int speed) {
    this.speed = speed;
  }

  public void addTag(IIdentificate tag) {
    tags.add(tag);
  }
}