package net.sf.anathema.character.equipment.impl.item.view;

import net.disy.commons.swing.border.TitledPanel;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.character.equipment.item.view.IEquipmentDatabaseView;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.lib.gui.list.actionview.IActionAddableListView;
import net.sf.anathema.lib.gui.list.actionview.SingleSelectionActionAddableListView;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;
import net.sf.anathema.lib.gui.selection.ListObjectSelectionView;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import java.awt.Dimension;

public class EquipmentDatabaseView implements IEquipmentDatabaseView {

  private JPanel contentPanel;
  private final JPanel editTemplateView = new JPanel(new GridDialogLayout(1, false));
  private final JPanel descriptionPanel = new JPanel(new GridDialogLayout(1, false));
  private final ListObjectSelectionView<String> templateListView = new ListObjectSelectionView<String>(String.class);
  private SingleSelectionActionAddableListView<IEquipmentStats> statsListView;
  private final JPanel editTemplateButtonPanel = new JPanel(new GridDialogLayout(1, false));
  private final JScrollPane templateListScrollPane = new JScrollPane(templateListView.getComponent());
  private final TitledPanel templateListPanel = new TitledPanel("", templateListScrollPane); //$NON-NLS-1$
  private final JPanel statsPanel = new JPanel(new GridDialogLayout(1, false));
  private final TitledPanel statsTitlePanel = new TitledPanel("", statsPanel); //$NON-NLS-1$

  @Override
  public JComponent getComponent() {
    if (contentPanel == null) {
      templateListScrollPane.setPreferredSize(new Dimension(150, 200));
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

  @Override
  public IActionAddableListView<IEquipmentStats> initStatsListView(ListCellRenderer renderer) {
    statsListView = new SingleSelectionActionAddableListView<IEquipmentStats>(null, IEquipmentStats.class);
    statsListView.setListCellRenderer(renderer);
    return statsListView;
  }

  private void fillStatsPanel() {
    statsPanel.add(new JScrollPane(statsListView.getComponent()), GridDialogLayoutData.FILL_BOTH);
  }

  @Override
  public void setTemplateListHeader(String headerText) {
    setTitleText(headerText, templateListPanel);
  }

  @Override
  public void setStatsListHeader(String headerText) {
    setTitleText(headerText, statsTitlePanel);
  }

  private void setTitleText(String headerText, TitledPanel panel) {
    TitledBorder titledBorder = (TitledBorder) ((CompoundBorder) panel.getBorder()).getOutsideBorder();
    titledBorder.setTitle(headerText);
  }

  @Override
  public void fillDescriptionPanel(JComponent component) {
    this.descriptionPanel.add(component, GridDialogLayoutData.FILL_HORIZONTAL);
  }

  @Override
  public IListObjectSelectionView<String> getTemplateListView() {
    return templateListView;
  }

  @Override
  public void addEditTemplateAction(Action action) {
    editTemplateButtonPanel.add(new JButton(action));
  }
}