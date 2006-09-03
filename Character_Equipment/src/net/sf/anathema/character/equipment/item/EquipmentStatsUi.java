/**
 * 
 */
package net.sf.anathema.character.equipment.item;

import javax.swing.Icon;

import net.disy.commons.swing.ui.IObjectUi;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;

public final class EquipmentStatsUi implements IObjectUi {
  public String getLabel(Object value) {
    return ((IEquipmentStats) value).getName().getId();
  }

  public Icon getIcon(Object value) {
    return null;
  }
}