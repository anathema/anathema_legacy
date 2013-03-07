package net.sf.anathema.character.equipment.impl.item.view;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.equipment.item.view.IEquipmentDatabaseView;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.lib.gui.container.TitledPanel;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.lib.gui.list.actionview.IActionAddableListView;
import net.sf.anathema.lib.gui.list.actionview.SingleSelectionActionAddableListView;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;
import net.sf.anathema.lib.gui.selection.ListObjectSelectionView;
import net.sf.anathema.framework.perspective.SwingPerspectivePane;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.fillWithoutInsets;
import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class EquipmentDatabaseView implements IEquipmentDatabaseView {
  private SwingPerspectivePane perspectivePane;
  private final JPanel descriptionPanel = new JPanel(new MigLayout(withoutInsets().wrapAfter(1)));
  private final ListObjectSelectionView<String> templateListView = new ListObjectSelectionView<>(String.class);
  private SingleSelectionActionAddableListView<IEquipmentStats> statsListView;
  private final JPanel editTemplateButtonPanel = new JPanel(new MigLayout(withoutInsets()));
  private final JComponent templateListPanel = new JScrollPane(templateListView.getComponent());
  private final JPanel statsPanel = new JPanel(new MigLayout(fillWithoutInsets().wrapAfter(1)));
  private final TitledPanel statsTitlePanel = new TitledPanel("", statsPanel);

  @Override
  public JComponent getComponent() {
    if (perspectivePane == null) {
      perspectivePane = new SwingPerspectivePane();
      JPanel detailPanel = new JPanel(new MigLayout(LayoutUtils.fillWithoutInsets().wrapAfter(1)));
      JPanel navigationPanel = new JPanel(new MigLayout(LayoutUtils.fillWithoutInsets().wrapAfter(1)));
      statsPanel.add(new JScrollPane(statsListView.getComponent()), new CC().grow().push());
      detailPanel.add(descriptionPanel, new CC().grow().pushX());
      detailPanel.add(statsTitlePanel, new CC().grow().push());
      navigationPanel.add(editTemplateButtonPanel);
      navigationPanel.add(templateListPanel, new CC().grow().push());
      perspectivePane.setContentComponent(detailPanel);
      perspectivePane.setNavigationComponent(navigationPanel);
    }
    return perspectivePane.getComponent();
  }

  @Override
  public IActionAddableListView<IEquipmentStats> initStatsListView(ListCellRenderer renderer) {
    statsListView = new SingleSelectionActionAddableListView<>(IEquipmentStats.class);
    statsListView.setListCellRenderer(renderer);
    return statsListView;
  }

  @Override
  public void setStatsListHeader(String headerText) {
    setTitleText(headerText, statsTitlePanel);
  }

  private void setTitleText(String headerText, TitledPanel panel) {
    TitledBorder titledBorder = (TitledBorder) (panel.getBorder());
    titledBorder.setTitle(headerText);
  }

  @Override
  public void fillDescriptionPanel(JComponent component) {
    this.descriptionPanel.add(component, new CC().push().grow());
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