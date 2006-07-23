package net.sf.anathema.dummy.character.equipment;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.equipment.character.model.IEquipmentObject;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;

public class DummyEquipmentObject implements IEquipmentObject {

  private final List<IEquipmentStats> allEquipments = new ArrayList<IEquipmentStats>();
  private final String name;
  private final String description;

  public DummyEquipmentObject(String title, String description) {
    this.name = title;
    this.description = description;
  }
  
  public void addEquipment(IEquipmentStats equipment) {
    this.allEquipments.add(equipment);
  }
  
  public IEquipmentStats[] getEquipments() {
    return allEquipments.toArray(new IEquipmentStats[allEquipments.size()]);
  }
  
  public String getName() {
    return name;
  }
  
  public String getDescription() {
    return description;
  }

  public void setPrintEnabled(IEquipmentStats equipment, boolean enabled) {
    //nothing to do;    
  }
}