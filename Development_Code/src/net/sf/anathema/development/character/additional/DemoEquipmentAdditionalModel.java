package net.sf.anathema.development.character.additional;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.equipment.impl.character.AbstractEquipmentAdditionalModel;
import net.sf.anathema.character.generic.equipment.weapon.IArmour;
import net.sf.anathema.character.generic.equipment.weapon.IWeapon;

public class DemoEquipmentAdditionalModel extends AbstractEquipmentAdditionalModel {

  private final List<IArmour> armours = new ArrayList<IArmour>();
  private final List<IWeapon> weapons = new ArrayList<IWeapon>();

  public void addPrintArmour(IArmour armour) {
    this.armours.add(armour);
  }

  public void addPrintWeapon(IWeapon weapon) {
    this.weapons.add(weapon);
  }

  public IArmour[] getPrintArmours() {
    return armours.toArray(new IArmour[armours.size()]);
  }

  public IWeapon[] getPrintWeapons() {
    return weapons.toArray(new IWeapon[weapons.size()]);
  }
}