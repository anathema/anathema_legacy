package net.sf.anathema.character.equipment.impl.character.model.stats;

import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class WeaponStatsDecorator implements IWeaponStats {
  private IWeaponStats stats;
  private ITraitType ability;
  private IIdentificate name;

  public WeaponStatsDecorator(IWeaponStats stats, AbilityType statsAbility) {
    this.stats = stats;
    this.ability = statsAbility;
    this.name = stats.getName();
  }

  public WeaponStatsDecorator(IWeaponStats stats, String name) {
    this.stats = stats;
    this.ability = stats.getTraitType();
    this.name = new Identificate(name);
  }

  public int getAccuracy() {
    return stats.getAccuracy();
  }

  public int getDamage() {
    return stats.getDamage();
  }

  public ITraitType getDamageTraitType() {
    return stats.getDamageTraitType();
  }

  public HealthType getDamageType() {
    return stats.getDamageType();
  }

  public Integer getDefence() {
    return stats.getDefence();
  }

  public Integer getRange() {
    return stats.getRange();
  }

  public Integer getRate() {
    return stats.getRate();
  }

  public int getSpeed() {
    return stats.getSpeed();
  }

  public IIdentificate[] getTags() {
    return stats.getTags();
  }

  public ITraitType getTraitType() {
    return ability;
  }

  public boolean inflictsNoDamage() {
    return stats.inflictsNoDamage();
  }

  public boolean isRangedCombat() {
    return stats.isRangedCombat();
  }

  public IEquipmentStats[] getViews() {
    return new IEquipmentStats[] { this };
  }

  public IIdentificate getName() {
    return name;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof WeaponStatsDecorator)) {
      return false;
    }
    WeaponStatsDecorator view = (WeaponStatsDecorator) obj;
    return view.stats.equals(stats) && view.ability.equals(ability);
  }

  @Override
  public int hashCode() {
    return stats.hashCode() + ability.hashCode();
  }

  @Override
  public String getId() {
    return name.getId() + "." + ability.getId(); //$NON-NLS-1$
  }
}
