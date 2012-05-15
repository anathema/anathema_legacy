package net.sf.anathema.character.equipment.item.view;

import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.gui.list.actionview.IActionAddableListView;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.ListCellRenderer;

public interface IEquipmentDatabaseView extends IView {

  void fillDescriptionPanel(JComponent content);

  IActionAddableListView<IEquipmentStats> initStatsListView(ListCellRenderer renderer);

  IListObjectSelectionView<String> getTemplateListView();

  void setTemplateListHeader(String headerText);

  void addEditTemplateAction(Action action);

  void setStatsListHeader(String headerText);
}