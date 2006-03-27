package net.sf.anathema.character.generic.impl.equipment;

import net.sf.anathema.character.generic.equipment.IWeaponStatistics;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.lib.util.IIdentificate;

public interface IEquippedWeapon extends IIdentificate, IWeaponStatistics {

  public AbilityType getAbility();
}