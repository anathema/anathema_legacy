package net.sf.anathema.character.equipment.character.model;

import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.lib.resources.IResources;

public interface IEquipmentPrintModel {
  IArmourStats[] getPrintArmours();

  IArmourStats getEffectivePrintArmour(IResources resources, int lineCount);

  IWeaponStats[] getPrintWeapons(IResources resources);
}