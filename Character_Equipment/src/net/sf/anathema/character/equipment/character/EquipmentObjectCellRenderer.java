package net.sf.anathema.character.equipment.character;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import net.sf.anathema.character.equipment.template.IEquipmentTemplate;

public class EquipmentObjectCellRenderer extends DefaultListCellRenderer {

  @Override
  public Component getListCellRendererComponent(
      JList list,
      Object value,
      int index,
      boolean isSelected,
      boolean cellHasFocus) {
    IEquipmentTemplate equipmentObject = (IEquipmentTemplate) value;
    return super.getListCellRendererComponent(list, equipmentObject.getName(), index, isSelected, cellHasFocus);
  }
  
  @Override
  public boolean equals(Object obj) {
    return obj instanceof EquipmentObjectCellRenderer;
  }
  
  @Override
  public int hashCode() {
    return 1;
  }
}