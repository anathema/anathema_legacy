package net.sf.anathema.character.equipment.impl.character.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.equipment.character.model.IEquipmentObject;
import net.sf.anathema.character.generic.equipment.weapon.IEquipment;

public class EquipmentObject implements IEquipmentObject {

  private List<IEquipment> printedEquipments = new ArrayList<IEquipment>();
  private final IEquipment[] equipments;
  private final String description;
  private final String name;

  public EquipmentObject(IEquipment[] equipments, String name, String description) {
    this.equipments = equipments;
    this.name = name;
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public IEquipment[] getEquipments() {
    return equipments;
  }

  public String getName() {
    return name;
  }

  public void setPrintEnabled(IEquipment equipment, boolean enabled) {
    if (enabled) {
      printedEquipments.add(equipment);
    }
    else {
      printedEquipments.remove(equipment);
    }
  }
}