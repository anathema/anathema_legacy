package net.sf.anathema.character.equipment.item.view;

import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.IVetoableObjectSelectionView;

public class AgnosticEquipmentDatabaseView implements EquipmentDatabaseView {

  private final EquipmentNavigation navigation;
  private final EquipmentDetails details;

  public AgnosticEquipmentDatabaseView(EquipmentNavigation navigation, EquipmentDetails details) {
    this.navigation = navigation;
    this.details = details;
  }

  @Override
  public ToolListView<IEquipmentStats> initStatsListView(
          AgnosticUIConfiguration<IEquipmentStats> configuration) {
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
  public IVetoableObjectSelectionView<String> getTemplateListView() {
    return navigation.getTemplateListView();
  }

  @Override
  public Tool addEditTemplateTool() {
    return navigation.addEditTemplateTool();
  }
}
