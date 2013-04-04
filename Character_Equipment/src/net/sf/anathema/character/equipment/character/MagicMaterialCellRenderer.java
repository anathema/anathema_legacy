package net.sf.anathema.character.equipment.character;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import java.awt.Component;

public final class MagicMaterialCellRenderer extends DefaultListCellRenderer {
private final Resources resources;

  public MagicMaterialCellRenderer(Resources resources) {
    this.resources = resources;
  }

  @Override
  public Component getListCellRendererComponent(
      JList list,
      Object value,
      int index,
      boolean isSelected,
      boolean cellHasFocus) {
    String text;
    if (value == null) {
      text = resources.getString("MagicMaterial.Unavailable");
    }
    else {
      MagicalMaterial material = (MagicalMaterial) value;
      text = resources.getString("MagicMaterial." + material.name());
    }
    return super.getListCellRendererComponent(list, text, index, isSelected, cellHasFocus);
  }
}