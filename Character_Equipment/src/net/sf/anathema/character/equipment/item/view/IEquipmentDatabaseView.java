package net.sf.anathema.character.equipment.item.view;

import net.sf.anathema.character.equipment.item.TechnologyAgnosticUIConfiguration;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.list.actionview.ToolListView;
import net.sf.anathema.lib.gui.selection.IVetoableObjectSelectionView;

import javax.swing.JComponent;

public interface IEquipmentDatabaseView {

  void fillDescriptionPanel(JComponent content);

  ToolListView<IEquipmentStats> initStatsListView(TechnologyAgnosticUIConfiguration<IEquipmentStats> configuration);

  IVetoableObjectSelectionView<String> getTemplateListView();

  Tool addEditTemplateTool();

  void setStatsListHeader(String headerText);

  JComponent getComponent();
}