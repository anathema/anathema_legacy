package net.sf.anathema.character.equipment.character;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import net.sf.anathema.character.equipment.character.model.IEquipmentObject;

public class EquipmentObjectCellRenderer extends DefaultListCellRenderer {

  @Override
  public Component getListCellRendererComponent(
      JList list,
      Object value,
      int index,
      boolean isSelected,
      boolean cellHasFocus) {
    IEquipmentObject equipmentObject = (IEquipmentObject) value;
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