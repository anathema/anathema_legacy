package net.sf.anathema.character.equipment.character;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class EquipmentObjectCellRenderer extends DefaultListCellRenderer {
	private static final long serialVersionUID = 1L;

@Override
  public Component getListCellRendererComponent(
      JList list,
      Object value,
      int index,
      boolean isSelected,
      boolean cellHasFocus) {
    String templateId = (String) value;
    return super.getListCellRendererComponent(list, templateId, index, isSelected, cellHasFocus);
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