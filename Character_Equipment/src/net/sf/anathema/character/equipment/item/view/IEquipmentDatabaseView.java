package net.sf.anathema.character.equipment.item.view;

import javax.swing.JComponent;
import javax.swing.ListCellRenderer;

import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;

public interface IEquipmentDatabaseView extends IView {

  public void fillDescriptionPanel(JComponent content);

  public IListObjectSelectionView<IEquipmentStats> getStatsListView();

  public IListObjectSelectionView<String> getTemplateListView();

  public IObjectSelectionView<IExaltedRuleSet> initRuleSetSelectionView(String label, ListCellRenderer renderer);

  public void setEditTemplateHeader(String headerText);

  public void setTemplateListHeader(String headerText);
}