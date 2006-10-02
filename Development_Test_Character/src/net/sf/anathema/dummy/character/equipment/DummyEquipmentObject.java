package net.sf.anathema.dummy.character.equipment;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;

public class DummyEquipmentObject implements IEquipmentItem {

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
  
  public IEquipmentStats[] getStats() {
    return allEquipments.toArray(new IEquipmentStats[allEquipments.size()]);
  }
  
  public String getTemplateId() {
    return name;
  }
  
  public String getDescription() {
    return description;
  }

  public void setPrintEnabled(IEquipmentStats equipment, boolean enabled) {
    //nothing to do;    
  }

  public boolean isPrintEnabled(IEquipmentStats stats) {
    return false;
  }
  
  public void setUnprinted() {
    //nothing to do    
  }
  
  public void setPrinted(String printedStatId) {
    //nothing to do
  }

  public MagicalMaterial getMaterial() {
    return null;
  }

  public MaterialComposition getMaterialComposition() {
    return MaterialComposition.None;
  }
}