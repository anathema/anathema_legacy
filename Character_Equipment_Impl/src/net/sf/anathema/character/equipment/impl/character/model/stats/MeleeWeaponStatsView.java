package net.sf.anathema.character.equipment.impl.character.model.stats;

import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class MeleeWeaponStatsView implements IWeaponStats {
  private MeleeWeaponStats stats;
  private AbilityType ability;

  public MeleeWeaponStatsView(MeleeWeaponStats stats, AbilityType statsAbility) {
    this.stats = stats;
    this.ability = statsAbility;
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
    return false;
  }

  public IEquipmentStats[] getViews() {
    return new IEquipmentStats[] { this };
  }

  public IIdentificate getName() {
    return new Identificate(this.stats.getName().getId() + " (" + ability.getId() + ")"); //$NON-NLS-1$//$NON-NLS-2$
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof MeleeWeaponStatsView)) {
      return false;
    }
    MeleeWeaponStatsView view = (MeleeWeaponStatsView) obj;
    return view.stats.equals(stats) && view.ability.equals(ability);
  }

  @Override
  public int hashCode() {
    return stats.hashCode() + ability.hashCode();
  }
}
