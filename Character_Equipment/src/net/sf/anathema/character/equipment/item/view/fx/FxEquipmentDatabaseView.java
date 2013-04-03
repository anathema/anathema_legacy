package net.sf.anathema.character.equipment.item.view.fx;

import net.sf.anathema.character.equipment.item.view.EquipmentDatabaseView;
import net.sf.anathema.character.equipment.item.view.EquipmentDescriptionPanel;
import net.sf.anathema.character.equipment.item.view.ToolListView;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.TechnologyAgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.IVetoableObjectSelectionView;
import net.sf.anathema.platform.fx.PerspectivePane;
import net.sf.anathema.platform.tool.FxButtonTool;

public class FxEquipmentDatabaseView implements EquipmentDatabaseView {
  public final PerspectivePane perspectivePane = new PerspectivePane();
  private final FxVetoableObjectSelectionView<String> templateListView = new FxVetoableObjectSelectionView<>();

  @Override
  public IVetoableObjectSelectionView<String> getTemplateListView() {
    return templateListView;
  }

  @Override
  public ToolListView<IEquipmentStats> initStatsListView(TechnologyAgnosticUIConfiguration<IEquipmentStats> configuration) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Tool addEditTemplateTool() {
    return new FxButtonTool();
  }

  @Override
  public void setStatsListHeader(String headerText) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public EquipmentDescriptionPanel addDescriptionPanel(String title) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }
}
