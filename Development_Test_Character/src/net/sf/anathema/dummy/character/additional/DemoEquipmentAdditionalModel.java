package net.sf.anathema.dummy.character.additional;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.equipment.character.model.IEquipmentObject;
import net.sf.anathema.character.equipment.impl.character.model.AbstractEquipmentAdditionalModel;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.control.collection.ICollectionListener;

public class DemoEquipmentAdditionalModel extends AbstractEquipmentAdditionalModel {

  private final List<IArmourStats> printArmourStats = new ArrayList<IArmourStats>();
  private final List<IWeaponStats> printWeaponStats = new ArrayList<IWeaponStats>();
  private final List<IEquipmentObject> availableEquipmentObjects = new ArrayList<IEquipmentObject>();
  private final List<IEquipmentObject> equipmentObjects = new ArrayList<IEquipmentObject>();
  private final GenericControl<ICollectionListener<IEquipmentObject>> equipmentObjectControl = new GenericControl<ICollectionListener<IEquipmentObject>>();

  public void addPrintArmour(IArmourStats armour) {
    this.printArmourStats.add(armour);
  }

  public void addPrintWeapon(IWeaponStats weapon) {
    this.printWeaponStats.add(weapon);
  }

  public IArmourStats[] getPrintArmours() {
    return printArmourStats.toArray(new IArmourStats[printArmourStats.size()]);
  }

  public IWeaponStats[] getPrintWeapons() {
    return printWeaponStats.toArray(new IWeaponStats[printWeaponStats.size()]);
  }

  public void addAvailableObject(IEquipmentObject object) {
    availableEquipmentObjects.add(object);
  }

  public void addEquipmentObject(final IEquipmentObject object) {
    equipmentObjects.add(object);
    equipmentObjectControl.forAllDo(new IClosure<ICollectionListener<IEquipmentObject>>() {
      public void execute(ICollectionListener<IEquipmentObject> input) {
        input.itemAdded(object);
      }
    });
  }

  public IEquipmentObject[] getAvailableObjects() {
    return availableEquipmentObjects.toArray(new IEquipmentObject[availableEquipmentObjects.size()]);
  }

  public void addEquipmentObjectListener(ICollectionListener<IEquipmentObject> listener) {
    equipmentObjectControl.addListener(listener);
  }
}