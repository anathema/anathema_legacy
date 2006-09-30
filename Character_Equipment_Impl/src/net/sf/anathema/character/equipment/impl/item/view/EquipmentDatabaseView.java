package net.sf.anathema.character.equipment.impl.item.view;

import java.awt.GridLayout;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import net.disy.commons.swing.border.TitledPanel;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.toolbar.ToolBarUtilities;
import net.sf.anathema.character.equipment.item.view.IEquipmentDatabaseView;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.lib.gui.list.actionview.IActionAddableListView;
import net.sf.anathema.lib.gui.list.actionview.SingleSelectionActionAddableListView;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.gui.selection.ListObjectSelectionView;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;

public class EquipmentDatabaseView implements IEquipmentDatabaseView {

  private JPanel contentPanel;
  private JPanel editTemplateView = new JPanel(new GridDialogLayout(1, false));
  private JLabel templateListHeaderLabel = new JLabel();
  private JLabel editTemplateHeaderLabel = new JLabel();
  private ListObjectSelectionView<String> templateListView = new ListObjectSelectionView<String>(String.class);
  private SingleSelectionActionAddableListView<IEquipmentStats> statsListView;
  private JPanel ruleSetPanel = new JPanel(new GridDialogLayout(2, false));
  private JPanel editTemplateButtonPanel = new JPanel(new GridLayout(1, 0));

  public JComponent getComponent() {
    if (contentPanel == null) {
      contentPanel = new JPanel(new GridDialogLayout(2, false));
      contentPanel.add(templateListHeaderLabel, GridDialogLayoutData.FILL_HORIZONTAL);
      contentPanel.add(createEditTemplateHeader(), GridDialogLayoutData.FILL_HORIZONTAL);
      contentPanel.add(new JScrollPane(templateListView.getComponent()), GridDialogLayoutData.FILL_BOTH);
      contentPanel.add(editTemplateView, GridDialogLayoutData.FILL_BOTH);
      editTemplateView.add(ceateStatsPanel(), GridDialogLayoutData.FILL_BOTH);
    }
    return contentPanel;
  }

  private JPanel createEditTemplateHeader() {
    JPanel headerPanel = new JPanel(new GridDialogLayout(2, false));
    headerPanel.add(editTemplateHeaderLabel);
    headerPanel.add(editTemplateButtonPanel);
    return headerPanel;
  }

  public IActionAddableListView<IEquipmentStats> initStatsListView(ListCellRenderer renderer) {
    statsListView = new SingleSelectionActionAddableListView<IEquipmentStats>(null, IEquipmentStats.class);
    statsListView.setListCellRenderer(renderer);
    return statsListView;
  }

  private JPanel ceateStatsPanel() {
    JPanel statsPanel = new JPanel(new GridDialogLayout(1, false));
    statsPanel.add(ruleSetPanel, GridDialogLayoutData.FILL_HORIZONTAL);
    statsPanel.add(new JScrollPane(statsListView.getComponent()), GridDialogLayoutData.FILL_BOTH);
    return new TitledPanel("Stats", statsPanel);
  }

  public IObjectSelectionView<IExaltedRuleSet> initRuleSetSelectionView(String label, ListCellRenderer renderer) {
    ObjectSelectionView<IExaltedRuleSet> view = new ObjectSelectionView<IExaltedRuleSet>(label, renderer);
    view.addTo(ruleSetPanel, GridDialogLayoutData.FILL_HORIZONTAL);
    return view;
  }

  public void setTemplateListHeader(String headerText) {
    templateListHeaderLabel.setText(headerText);
  }

  public void setEditTemplateHeader(String headerText) {
    editTemplateHeaderLabel.setText(headerText);
  }

  public void fillDescriptionPanel(JComponent descriptionPanel) {
    editTemplateView.add(descriptionPanel, GridDialogLayoutData.FILL_HORIZONTAL);
  }

  public IListObjectSelectionView<String> getTemplateListView() {
    return templateListView;
  }

  public void addEditTemplateAction(Action action) {
    editTemplateButtonPanel.add(ToolBarUtilities.createToolBarButton(action));
  }
}