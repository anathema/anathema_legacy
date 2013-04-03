package net.sf.anathema.character.equipment.item.view;

import net.sf.anathema.character.equipment.item.EquipmentDescriptionPanel;
import net.sf.anathema.character.equipment.item.TechnologyAgnosticUIConfiguration;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.list.actionview.ToolListView;
import net.sf.anathema.lib.gui.selection.IVetoableObjectSelectionView;

public interface IEquipmentDatabaseView {

  ToolListView<IEquipmentStats> initStatsListView(TechnologyAgnosticUIConfiguration<IEquipmentStats> configuration);

  IVetoableObjectSelectionView<String> getTemplateListView();

  Tool addEditTemplateTool();

  void setStatsListHeader(String headerText);

  EquipmentDescriptionPanel addDescriptionPanel(String title);
}