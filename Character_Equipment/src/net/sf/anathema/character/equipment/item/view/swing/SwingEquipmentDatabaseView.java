package net.sf.anathema.character.equipment.item.view.swing;

import net.sf.anathema.character.equipment.item.view.EquipmentDatabaseView;
import net.sf.anathema.character.equipment.item.view.EquipmentDescriptionPanel;
import net.sf.anathema.character.equipment.item.view.ToolListView;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.framework.perspective.SwingPerspectivePane;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.TechnologyAgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;

public class SwingEquipmentDatabaseView implements EquipmentDatabaseView {
  public final SwingPerspectivePane perspectivePane = new SwingPerspectivePane();
  private final SwingEquipmentNavigation navigation = new SwingEquipmentNavigation();
  private final SwingEquipmentDetails details = new SwingEquipmentDetails();

  public SwingEquipmentDatabaseView() {
    perspectivePane.setNavigationComponent(navigation.getComponent());
    perspectivePane.setContentComponent(details.getComponent());
  }

  @Override
  public ToolListView<IEquipmentStats> initStatsListView(
          TechnologyAgnosticUIConfiguration<IEquipmentStats> configuration) {
    return details.initStatsListView(configuration);
  }

  @Override
  public void setStatsListHeader(String headerText) {
    details.setStatsListHeader(headerText);
  }

  @Override
  public EquipmentDescriptionPanel addDescriptionPanel(String title) {
    return details.addDescriptionPanel(title);
  }

  @Override
  public IListObjectSelectionView<String> getTemplateListView() {
    return navigation.getTemplateListView();
  }

  @Override
  public Tool addEditTemplateTool() {
    return navigation.addTool();
  }
}