package net.sf.anathema.character.equipment.impl.item.view;

import java.awt.Dimension;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

import net.disy.commons.swing.border.TitledPanel;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
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
  private final JPanel editTemplateView = new JPanel(new GridDialogLayout(1, false));
  private final JPanel descriptionPanel = new JPanel(new GridDialogLayout(1, false));
  private final ListObjectSelectionView<String> templateListView = new ListObjectSelectionView<String>(String.class);
  private SingleSelectionActionAddableListView<IEquipmentStats> statsListView;
  private final JPanel ruleSetPanel = new JPanel(new GridDialogLayout(2, false));
  private final JPanel editTemplateButtonPanel = new JPanel(new GridDialogLayout(1, false));
  private final TitledPanel templateListPanel = new TitledPanel("", new JScrollPane(templateListView.getComponent())); //$NON-NLS-1$
  private final JPanel statsPanel = new JPanel(new GridDialogLayout(1, false));
  private TitledPanel statsTitlePanel = new TitledPanel("", statsPanel); //$NON-NLS-1$

  public JComponent getComponent() {
    if (contentPanel == null) {
      templateListView.getComponent().setPreferredSize(new Dimension(150, 200));
      contentPanel = new JPanel(new GridDialogLayout(3, false));
      contentPanel.add(templateListPanel, GridDialogLayoutData.FILL_BOTH);
      contentPanel.add(editTemplateButtonPanel);
      contentPanel.add(editTemplateView, GridDialogLayoutData.FILL_BOTH);
      editTemplateView.add(descriptionPanel, GridDialogLayoutData.FILL_HORIZONTAL);
      fillStatsPanel();
      editTemplateView.add(statsTitlePanel, GridDialogLayoutData.FILL_BOTH);
    }
    return contentPanel;
  }

  public IActionAddableListView<IEquipmentStats> initStatsListView(ListCellRenderer renderer) {
    statsListView = new SingleSelectionActionAddableListView<IEquipmentStats>(null, IEquipmentStats.class);
    statsListView.setListCellRenderer(renderer);
    return statsListView;
  }

  private void fillStatsPanel() {
    statsPanel.add(ruleSetPanel, GridDialogLayoutData.FILL_HORIZONTAL);
    statsPanel.add(new JScrollPane(statsListView.getComponent()), GridDialogLayoutData.FILL_BOTH);
  }

  public IObjectSelectionView<IExaltedRuleSet> initRuleSetSelectionView(String label, ListCellRenderer renderer) {
    ObjectSelectionView<IExaltedRuleSet> view = new ObjectSelectionView<IExaltedRuleSet>(label, renderer);
    view.addTo(ruleSetPanel, GridDialogLayoutData.FILL_HORIZONTAL);
    return view;
  }

  public void setTemplateListHeader(String headerText) {
    setTitleText(headerText, templateListPanel);
  }

  public void setStatsListHeader(String headerText) {
    setTitleText(headerText, statsTitlePanel);
  }

  private void setTitleText(String headerText, TitledPanel panel) {
    TitledBorder titledBorder = (TitledBorder) ((CompoundBorder) panel.getBorder()).getOutsideBorder();
    titledBorder.setTitle(headerText);
  }

  public void fillDescriptionPanel(JComponent component) {
    this.descriptionPanel.add(component, GridDialogLayoutData.FILL_HORIZONTAL);
  }

  public IListObjectSelectionView<String> getTemplateListView() {
    return templateListView;
  }

  public void addEditTemplateAction(Action action) {
    editTemplateButtonPanel.add(new JButton(action));
  }
}