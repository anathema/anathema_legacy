package net.sf.anathema.character.equipment.impl.character.model.stats;

import com.google.common.base.Objects;
import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.equipment.impl.character.model.ModifierFactory;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.AccuracyModification;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.BaseMaterial;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.DamageModification;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.DefenseModification;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.RangeModification;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.RateModification;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.SpeedModification;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.StatModifier;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.StatsModification;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.TagsModification;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.WeaponStatsType;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.equipment.EquipmentAccuracyModifier;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.equipment.EquipmentDamageModifier;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.equipment.EquipmentDefenceModifier;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.equipment.EquipmentRateModifier;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.equipment.EquipmentSpeedModifier;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.material.MaterialAccuracyModifier;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.material.MaterialDamageModifier;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.material.MaterialDefenceModifier;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.material.MaterialRangeModifier;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.material.MaterialRateModifier;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.material.MaterialSpeedModifier;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.modifier.AttunementModifier;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.modifier.BestModifier;
import net.sf.anathema.character.equipment.impl.creation.model.WeaponTag;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.util.IProxy;
import net.sf.anathema.lib.util.IIdentificate;

public class ProxyWeaponStats extends AbstractStats implements IWeaponStats, IProxy<IWeaponStats> {

  private final IWeaponStats delegate;
  private final BaseMaterial material;
  private final ModifierFactory modifiers;

  public ProxyWeaponStats(IWeaponStats stats, BaseMaterial material, ModifierFactory modifiers) {
    this.delegate = stats;
    this.material = material;
    this.modifiers = modifiers;
  }

  @Override
  public IWeaponStats getUnderlying() {
    return this.delegate;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof ProxyWeaponStats)) {
      return delegate != null && delegate.equals(obj);
    }
    ProxyWeaponStats other = (ProxyWeaponStats) obj;
    return Objects.equal(delegate, other.delegate) && Objects.equal(material, other.material);
  }

  @Override
  public int hashCode() {
    return delegate.hashCode();
  }

  @Override
  public int getAccuracy() {
    int accuracy = delegate.getAccuracy();
    WeaponStatsType type = getWeaponStatsType();
    StatModifier material = createAttunementModifier(new MaterialAccuracyModifier(this.material, type));
    StatModifier equipment = new EquipmentAccuracyModifier(modifiers.createModifiers(), type);
    StatModifier modifier = new BestModifier(material, equipment);
    return getModifiedValue(new AccuracyModification(modifier), accuracy);
  }

  private WeaponStatsType getWeaponStatsType() {
    if (ArrayUtilities.containsValue(getTags(), WeaponTag.BowType)) {
      return WeaponStatsType.Bow;
    }
    if (ArrayUtilities.containsValue(getTags(), WeaponTag.FlameType)) {
      return WeaponStatsType.Flame;
    }
    if (ArrayUtilities.containsValue(getTags(), WeaponTag.Thrown)) {
      if (ArrayUtilities.containsValue(getTags(), WeaponTag.BowBonuses)) {
        return WeaponStatsType.Thrown_BowBonuses;
      } else {
        return WeaponStatsType.Thrown;
      }
    }
    return WeaponStatsType.Melee;
  }

  @Override
  public int getDamage() {
    int damage = delegate.getDamage();
    StatModifier material = createAttunementModifier(new MaterialDamageModifier(this.material, getWeaponStatsType()));
    StatModifier equipment = new EquipmentDamageModifier(modifiers.createModifiers(), getWeaponStatsType());
    StatModifier modifier = new BestModifier(material, equipment);
    return getModifiedValue(new DamageModification(modifier), damage);
  }


  @Override
  public int getMinimumDamage() {
    return delegate.getMinimumDamage();
  }

  @Override
  public ITraitType getDamageTraitType() {
    return delegate.getDamageTraitType();
  }

  @Override
  public HealthType getDamageType() {
    return delegate.getDamageType();
  }

  @Override
  public Integer getDefence() {
    Integer defence = delegate.getDefence();
    StatModifier material = createAttunementModifier(new MaterialDefenceModifier(this.material));
    StatModifier equipment = new EquipmentDefenceModifier(modifiers.createModifiers());
    StatModifier modifier = new BestModifier(material, equipment);
    return getModifiedValue(new DefenseModification(modifier), defence);
  }

  @Override
  public int getMobilityPenalty() {
    return delegate.getMobilityPenalty();
  }

  @Override
  public Integer getRange() {
    Integer range = delegate.getRange();
    StatModifier modifier = createAttunementModifier(new MaterialRangeModifier(material, getWeaponStatsType()));
    return getModifiedValue(new RangeModification(modifier), range);
  }

  @Override
  public Integer getRate() {
    Integer rate = delegate.getRate();
    WeaponStatsType type = getWeaponStatsType();
    StatModifier material = createAttunementModifier(new MaterialRateModifier(this.material, type));
    StatModifier equipment = new EquipmentRateModifier(modifiers.createModifiers(), type);
    StatModifier modifier = new BestModifier(material, equipment);
    return getModifiedValue(new RateModification(modifier), rate);
  }

  @Override
  public int getSpeed() {
    int speed = delegate.getSpeed();
    StatModifier material = createAttunementModifier(new MaterialSpeedModifier(this.material));
    StatModifier equipment = new EquipmentSpeedModifier(modifiers.createModifiers(), getWeaponStatsType());
    StatModifier modifier = new BestModifier(material, equipment);
    return getModifiedValue(new SpeedModification(modifier), speed);
  }

  @Override
  public IIdentificate[] getTags() {
    return new TagsModification(material).getModifiedValue(delegate.getTags());
  }

  private AttunementModifier createAttunementModifier(StatModifier modifier) {
    return new AttunementModifier(modifier);
  }

  @Override
  public ITraitType getTraitType() {
    return delegate.getTraitType();
  }

  @Override
  public boolean inflictsNoDamage() {
    return delegate.inflictsNoDamage();
  }

  @Override
  public IIdentificate getName() {
    return delegate.getName();
  }

  @Override
  public boolean isRangedCombat() {
    return delegate.isRangedCombat();
  }

  @Override
  public IEquipmentStats[] getViews() {
    return new IEquipmentStats[]{this};
  }

  @Override
  public String getId() {
    return delegate.getId();
  }

  @Override
  public boolean representsItemForUseInCombat() {
    return delegate.representsItemForUseInCombat();
  }

  private Integer getModifiedValue(StatsModification modification, Integer unmodifiedValue) {
    if (unmodifiedValue == null) {
      return null;
    }
    return modification.getModifiedValue(unmodifiedValue);
  }
}