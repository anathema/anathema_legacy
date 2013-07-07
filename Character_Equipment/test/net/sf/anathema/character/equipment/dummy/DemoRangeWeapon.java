package net.sf.anathema.character.equipment.dummy;

import net.sf.anathema.character.equipment.impl.character.model.stats.AbstractCombatStats;
import net.sf.anathema.character.main.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.main.equipment.weapon.IWeaponStats;
import net.sf.anathema.hero.health.HealthType;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class DemoRangeWeapon extends AbstractCombatStats implements IWeaponStats {

  private final int speed;
  private final int accuracy;
  private final int damage;
  private final int minimumDamage;
  private final HealthType damageType;
  private final int range;
  private final Identifier name;
  private final int rate;
  private final boolean isNoDamage;

  public DemoRangeWeapon(SimpleIdentifier name, int speed, int accuracy, int damage, int minimumDamage, HealthType damageType, int range, int rate,
                         boolean isNoDamage) {
    this.name = name;
    this.speed = speed;
    this.accuracy = accuracy;
    this.damage = damage;
    this.minimumDamage = minimumDamage;
    this.damageType = damageType;
    this.range = range;
    this.rate = rate;
    this.isNoDamage = isNoDamage;
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
  public int getMinimumDamage() {
    return minimumDamage;
  }

  @Override
  public HealthType getDamageType() {
    return damageType;
  }

  @Override
  public Integer getDefence() {
    return null;
  }

  @Override
  public int getMobilityPenalty() {
    return 0;
  }

  @Override
  public Integer getRange() {
    return range;
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
  public Identifier[] getTags() {
    return new Identifier[0];
  }

  @Override
  public TraitType getTraitType() {
    return AbilityType.MartialArts;
  }

  @Override
  public TraitType getDamageTraitType() {
    return null;
  }

  @Override
  public boolean inflictsNoDamage() {
    return isNoDamage;
  }

  @Override
  public Identifier getName() {
    return name;
  }

  @Override
  public boolean isRangedCombat() {
    return true;
  }

  @Override
  public IEquipmentStats[] getViews() {
    return new IEquipmentStats[]{this};
  }

  @Override
  public String getId() {
    return getName().getId();
  }
}