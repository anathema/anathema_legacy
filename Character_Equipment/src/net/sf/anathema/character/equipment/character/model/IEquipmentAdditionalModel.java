package net.sf.anathema.character.equipment.character.model;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;

public interface IEquipmentAdditionalModel extends IAdditionalModel, IEquipmentObjectCollection {

  public IWeaponStats[] getPrintWeapons();

  public IArmourStats[] getPrintArmours();

  public IArmourStats getTotalPrintArmour(int lineCount);
}