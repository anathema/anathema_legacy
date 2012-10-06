package net.sf.anathema.character.equipment.character;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import java.awt.Component;

public final class MagicMaterialCellRenderer extends DefaultListCellRenderer {
private final IResources resources;

  public MagicMaterialCellRenderer(IResources resources) {
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
      text = resources.getString("MagicMaterial.Unavailable"); //$NON-NLS-1$
    }
    else {
      MagicalMaterial material = (MagicalMaterial) value;
      text = resources.getString("MagicMaterial." + material.name()); //$NON-NLS-1$
    }
    return super.getListCellRendererComponent(list, text, index, isSelected, cellHasFocus);
  }
}