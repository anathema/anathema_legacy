package net.sf.anathema.character.equipment.impl.character.model.stats;

import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class WeaponStatsDecorator extends AbstractStats implements IWeaponStats {
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

  @Override
  public int getAccuracy() {
    return stats.getAccuracy();
  }

  @Override
  public int getDamage() {
    return stats.getDamage();
  }
  
  @Override
  public int getMinimumDamage() {
	return stats.getMinimumDamage();
  }

  @Override
  public ITraitType getDamageTraitType() {
    return stats.getDamageTraitType();
  }

  @Override
  public HealthType getDamageType() {
    return stats.getDamageType();
  }

  @Override
  public Integer getDefence() {
    return stats.getDefence();
  }
  
  @Override
  public int getMobilityPenalty() {
	return stats.getMobilityPenalty();
  }

  @Override
  public Integer getRange() {
    return stats.getRange();
  }

  @Override
  public Integer getRate() {
    return stats.getRate();
  }

  @Override
  public int getSpeed() {
    return stats.getSpeed();
  }

  @Override
  public IIdentificate[] getTags() {
    return stats.getTags();
  }

  @Override
  public ITraitType getTraitType() {
    return ability;
  }

  @Override
  public boolean inflictsNoDamage() {
    return stats.inflictsNoDamage();
  }

  @Override
  public boolean isRangedCombat() {
    return stats.isRangedCombat();
  }

  @Override
  public IEquipmentStats[] getViews() {
    return new IEquipmentStats[] { this };
  }

  @Override
  public IIdentificate getName() {
    return name;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof WeaponStatsDecorator)) {
      return stats.equals(obj);
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

  @Override
  public boolean representsItemForUseInCombat() {
    return stats.representsItemForUseInCombat();
  }
}
