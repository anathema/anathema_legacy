package net.sf.anathema.character.equipment.item.view;

import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;

public interface EquipmentDetails {

  ToolListView<IEquipmentStats> initStatsListView(AgnosticUIConfiguration<IEquipmentStats> configuration);

  void setStatsListHeader(String headerText);

  EquipmentDescriptionPanel addDescriptionPanel(String title);
}