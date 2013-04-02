package net.sf.anathema.character.equipment.item.view;

import net.sf.anathema.character.equipment.item.TechnologyAgnosticUIConfiguration;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.list.actionview.IActionAddableListView;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;

import javax.swing.JComponent;

public interface IEquipmentDatabaseView {

  void fillDescriptionPanel(JComponent content);

  IActionAddableListView<IEquipmentStats> initStatsListView(TechnologyAgnosticUIConfiguration<IEquipmentStats> configuration);

  IListObjectSelectionView<String> getTemplateListView();

  Tool addEditTemplateTool();

  void setStatsListHeader(String headerText);

  JComponent getComponent();
}