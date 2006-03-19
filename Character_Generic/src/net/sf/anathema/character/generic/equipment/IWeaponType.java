package net.sf.anathema.character.generic.equipment;

import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.lib.util.IIdentificate;

public interface IWeaponType extends IIdentificate, IWeaponStatistics {

  public AbilityType[] getAllowedAbilities();
}