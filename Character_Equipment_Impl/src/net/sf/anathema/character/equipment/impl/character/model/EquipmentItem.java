package net.sf.anathema.character.equipment.impl.character.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.character.model.IEquipmentTemplate;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;

public class EquipmentItem implements IEquipmentItem {

  private List<IEquipmentStats> printedEquipments = new ArrayList<IEquipmentStats>();
  private final IEquipmentTemplate template;

  public EquipmentItem(IEquipmentTemplate template) {
    this.template = template;
  }

  public String getDescription() {
    return template.getDescription();
  }

  public IEquipmentStats[] getEquipments() {
    return template.getEquipmentStats();
  }

  public String getName() {
    return template.getName();
  }

  public void setPrintEnabled(IEquipmentStats equipment, boolean enabled) {
    if (enabled) {
      printedEquipments.add(equipment);
    }
    else {
      printedEquipments.remove(equipment);
    }
  }
}