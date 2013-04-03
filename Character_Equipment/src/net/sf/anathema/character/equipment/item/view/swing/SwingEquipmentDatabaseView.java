package net.sf.anathema.character.equipment.item.view.swing;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.lib.gui.ConfigurableSwingUI;
import net.sf.anathema.character.equipment.item.view.EquipmentDescriptionPanel;
import net.sf.anathema.lib.gui.TechnologyAgnosticUIConfiguration;
import net.sf.anathema.character.equipment.item.view.EquipmentDatabaseView;
import net.sf.anathema.character.equipment.item.view.ToolListView;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.framework.perspective.PerspectiveToolBar;
import net.sf.anathema.framework.perspective.SwingPerspectivePane;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.SwingActionTool;
import net.sf.anathema.lib.gui.container.TitledPanel;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;
import net.sf.anathema.lib.gui.selection.ListObjectSelectionView;
import net.sf.anathema.lib.gui.ui.ObjectUiListCellRenderer;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.fillWithoutInsets;
import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class SwingEquipmentDatabaseView implements EquipmentDatabaseView {
  private SwingPerspectivePane perspectivePane;
  private final JPanel descriptionPanel = new JPanel(new MigLayout(withoutInsets().wrapAfter(1)));
  private final ListObjectSelectionView<String> templateListView = new ListObjectSelectionView<>(String.class);
  private SingleSelectionToolListView<IEquipmentStats> statsListView;
  private final PerspectiveToolBar editTemplateButtonPanel = new PerspectiveToolBar();
  private final JComponent templateListPanel = new JScrollPane(templateListView.getComponent());
  private final JPanel statsPanel = new JPanel(new MigLayout(fillWithoutInsets().wrapAfter(1)));
  private final TitledPanel statsTitlePanel = new TitledPanel("", statsPanel);

  public JComponent getComponent() {
    if (perspectivePane == null) {
      perspectivePane = new SwingPerspectivePane();
      JPanel detailPanel = new JPanel(new MigLayout(LayoutUtils.fillWithoutInsets().wrapAfter(1)));
      JPanel navigationPanel = new JPanel(new MigLayout(LayoutUtils.fillWithoutInsets().wrapAfter(1)));
      statsPanel.add(new JScrollPane(statsListView.getComponent()), new CC().grow().push());
      detailPanel.add(descriptionPanel, new CC().grow().pushX());
      detailPanel.add(statsTitlePanel, new CC().grow().push());
      navigationPanel.add(editTemplateButtonPanel.getComponent());
      navigationPanel.add(templateListPanel, new CC().grow().push());
      perspectivePane.setContentComponent(detailPanel);
      perspectivePane.setNavigationComponent(navigationPanel);
    }
    return perspectivePane.getComponent();
  }

  @Override
  public ToolListView<IEquipmentStats> initStatsListView(TechnologyAgnosticUIConfiguration<IEquipmentStats> configuration) {
    statsListView = new SingleSelectionToolListView<>(IEquipmentStats.class);
    ListCellRenderer renderer = new ObjectUiListCellRenderer(new ConfigurableSwingUI<>(configuration));
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
  public EquipmentDescriptionPanel addDescriptionPanel(String title) {
    SwingEquipmentDescriptionPanel panel = new SwingEquipmentDescriptionPanel(title);
    this.descriptionPanel.add(panel.getContent(), new CC().push().grow());
    return panel;
  }

  @Override
  public IListObjectSelectionView<String> getTemplateListView() {
    return templateListView;
  }

  @Override
  public Tool addEditTemplateTool() {
    SwingActionTool tool = new SwingActionTool();
    editTemplateButtonPanel.addTools(tool.getAction());
    return tool;
  }
}