package net.sf.anathema.character.equipment.item.view;

import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.lib.gui.TechnologyAgnosticUIConfiguration;

public interface EquipmentDetails {

  ToolListView<IEquipmentStats> initStatsListView(TechnologyAgnosticUIConfiguration<IEquipmentStats> configuration);

  void setStatsListHeader(String headerText);

  EquipmentDescriptionPanel addDescriptionPanel(String title);
}