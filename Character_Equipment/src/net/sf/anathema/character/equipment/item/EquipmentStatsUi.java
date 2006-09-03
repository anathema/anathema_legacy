package net.sf.anathema.character.equipment.item;

import javax.swing.Icon;

import net.disy.commons.swing.ui.IObjectUi;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.lib.util.IIdentificate;

public final class EquipmentStatsUi implements IObjectUi {
  public String getLabel(Object value) {
    IIdentificate name = ((IEquipmentStats) value).getName();
    if (name == null) {
      return "Default";
    }
    return name.getId();
  }

  public Icon getIcon(Object value) {
    return null;
  }
}