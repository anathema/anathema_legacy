package net.sf.anathema.character.equipment.item.view;

import net.sf.anathema.character.main.equipment.weapon.IEquipmentStats;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;

public interface EquipmentDetails {

  ToolListView<IEquipmentStats> initStatsListView(String title, AgnosticUIConfiguration<IEquipmentStats> configuration);

  EquipmentDescriptionPanel addDescriptionPanel(String title);
}