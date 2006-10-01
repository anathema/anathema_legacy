package net.sf.anathema.character.equipment.item.view;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.ListCellRenderer;

import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.gui.list.actionview.IActionAddableListView;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;

public interface IEquipmentDatabaseView extends IView {

  public void fillDescriptionPanel(JComponent content);

  public IActionAddableListView<IEquipmentStats> initStatsListView(ListCellRenderer renderer);

  public IListObjectSelectionView<String> getTemplateListView();

  public IObjectSelectionView<IExaltedRuleSet> initRuleSetSelectionView(String label, ListCellRenderer renderer);

  public void setTemplateListHeader(String headerText);

  public void addEditTemplateAction(Action action);
}