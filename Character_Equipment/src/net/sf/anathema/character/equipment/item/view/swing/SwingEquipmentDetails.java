package net.sf.anathema.character.equipment.item.view.swing;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.equipment.item.view.EquipmentDescriptionPanel;
import net.sf.anathema.character.equipment.item.view.EquipmentDetails;
import net.sf.anathema.character.equipment.item.view.ToolListView;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.ConfigurableSwingUI;
import net.sf.anathema.lib.gui.container.TitledPanel;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.lib.gui.ui.ObjectUiListCellRenderer;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.fillWithoutInsets;
import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class SwingEquipmentDetails implements EquipmentDetails {
  private JComponent detailsPanel = new JPanel(new MigLayout(LayoutUtils.fillWithoutInsets().wrapAfter(1)));
  private final SingleSelectionToolListView<IEquipmentStats> statsListView = new SingleSelectionToolListView<>(IEquipmentStats.class);
  private final JPanel statsPanel = new JPanel(new MigLayout(fillWithoutInsets().wrapAfter(1)));
  private final TitledPanel statsTitlePanel = new TitledPanel("", statsPanel);
  private final JPanel descriptionPanel = new JPanel(new MigLayout(withoutInsets().wrapAfter(1)));


  public SwingEquipmentDetails() {
    statsPanel.add(new JScrollPane(statsListView.getComponent()), new CC().grow().push());
    detailsPanel.add(descriptionPanel, new CC().grow().pushX());
    detailsPanel.add(statsTitlePanel, new CC().grow().push());
  }

  public JComponent getComponent() {
    return detailsPanel;
  }

  public ToolListView<IEquipmentStats> initStatsListView(
          String title, AgnosticUIConfiguration<IEquipmentStats> configuration) {
    ListCellRenderer renderer = new ObjectUiListCellRenderer(new ConfigurableSwingUI<>(configuration));
    statsListView.setListCellRenderer(renderer);
    setTitleText(title, statsTitlePanel);
    return statsListView;
  }


  private void setTitleText(String headerText, TitledPanel panel) {
    TitledBorder titledBorder = (TitledBorder) (panel.getBorder());
    titledBorder.setTitle(headerText);
  }

  public EquipmentDescriptionPanel addDescriptionPanel(String title) {
    SwingEquipmentDescriptionPanel panel = new SwingEquipmentDescriptionPanel(title);
    descriptionPanel.add(panel.getContent(), new CC().push().grow());
    return panel;
  }
}