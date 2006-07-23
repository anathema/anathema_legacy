package net.sf.anathema.dummy.character.additional;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.equipment.character.model.IEquipmentObject;
import net.sf.anathema.character.equipment.impl.character.model.AbstractEquipmentAdditionalModel;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;

public class DemoEquipmentAdditionalModel extends AbstractEquipmentAdditionalModel {

  private final List<IArmourStats> armours = new ArrayList<IArmourStats>();
  private final List<IWeaponStats> weapons = new ArrayList<IWeaponStats>();
  private final List<IEquipmentObject> objects = new ArrayList<IEquipmentObject>();

  public void addPrintArmour(IArmourStats armour) {
    this.armours.add(armour);
  }

  public void addPrintWeapon(IWeaponStats weapon) {
    this.weapons.add(weapon);
  }

  public IArmourStats[] getPrintArmours() {
    return armours.toArray(new IArmourStats[armours.size()]);
  }

  public IWeaponStats[] getPrintWeapons() {
    return weapons.toArray(new IWeaponStats[weapons.size()]);
  }
  
  public void addObject(IEquipmentObject object) {
    objects.add(object);
  }

  public IEquipmentObject[] getAvailableObjects() {
    return objects.toArray(new IEquipmentObject[objects.size()]);
  }
}