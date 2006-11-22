package net.sf.anathema.character.equipment.character.model;

import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IShieldStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;

public interface IEquipmentPrintModel {
  public IArmourStats[] getPrintArmours();

  public IArmourStats getTotalPrintArmour(int lineCount);

  public IWeaponStats[] getPrintWeapons();

  public IShieldStats[] getPrintShield();
}