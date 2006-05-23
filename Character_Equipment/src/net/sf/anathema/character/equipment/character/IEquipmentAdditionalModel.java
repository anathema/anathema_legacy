package net.sf.anathema.character.equipment.character;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.equipment.weapon.IArmour;
import net.sf.anathema.character.generic.equipment.weapon.IWeapon;

public interface IEquipmentAdditionalModel extends IAdditionalModel {

  public IWeapon[] getPrintWeapons();

  public IArmour[] getPrintArmours();
}