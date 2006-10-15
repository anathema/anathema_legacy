package net.sf.anathema.character.equipment.impl.character.model.natural;

import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.health.HealthType;

public abstract class AbstractNaturalWeaponStats implements IWeaponStats {

  public final HealthType getDamageType() {
    return HealthType.Bashing;
  }

  public final Integer getRange() {
    return null;
  }

  public Integer getRate() {
    return null;
  }

  public final boolean isRangedCombat() {
    return false;
  }
}