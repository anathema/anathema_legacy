package net.sf.anathema.character.equipment.item;

import javax.swing.Icon;

import net.disy.commons.swing.ui.IObjectUi;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.EquipmentUI;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.lib.resources.IResources;

public final class EquipmentStatsUi implements IObjectUi {
  
  private EquipmentUI equipmentUI;

  public EquipmentStatsUi(IResources resources) {
    equipmentUI = new EquipmentUI(resources);
  }
  
  public String getLabel(Object value) {
    return ((IEquipmentStats) value).getName().getId();
  }

  public Icon getIcon(Object value) {
    return null;
  }
}