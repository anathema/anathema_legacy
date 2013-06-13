package net.sf.anathema.character.equipment.impl.character.model.natural;

import net.sf.anathema.character.equipment.impl.character.model.stats.AbstractCombatStats;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.health.HealthType;

public abstract class AbstractNaturalWeaponStats extends AbstractCombatStats implements IWeaponStats {

  @Override
  public final HealthType getDamageType() {
    return HealthType.Bashing;
  }

  @Override
  public final Integer getRange() {
    return null;
  }

  @Override
  public Integer getRate() {
    return null;
  }

  @Override
  public final boolean isRangedCombat() {
    return false;
  }

  @Override
  public int getMobilityPenalty() {
    return 0;
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