package net.sf.anathema.dummy.character.equipment;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.equipment.character.model.IEquipmentObject;
import net.sf.anathema.character.generic.equipment.weapon.IEquipment;

public class DummyEquipmentObject implements IEquipmentObject {

  private final List<IEquipment> allEquipments = new ArrayList<IEquipment>();
  private final String name;
  private final String description;

  public DummyEquipmentObject(String title, String description) {
    this.name = title;
    this.description = description;
  }
  
  public void addEquipment(IEquipment equipment) {
    this.allEquipments.add(equipment);
  }
  
  public IEquipment[] getEquipments() {
    return allEquipments.toArray(new IEquipment[allEquipments.size()]);
  }
  
  public String getName() {
    return name;
  }
  
  public String getDescription() {
    return description;
  }

  public void setPrintEnabled(IEquipment equipment, boolean enabled) {
    //nothing to do;    
  }
}