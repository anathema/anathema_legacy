package net.sf.anathema.character.equipment.item.view.fx;

import javafx.scene.Node;
import net.sf.anathema.character.equipment.item.view.EquipmentDescriptionPanel;
import net.sf.anathema.character.equipment.item.view.EquipmentDetails;
import net.sf.anathema.character.equipment.item.view.ToolListView;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.lib.gui.TechnologyAgnosticUIConfiguration;

public class FxEquipmentDetails implements EquipmentDetails {
  public Node getNode() {
    return null;  //To change body of created methods use File | Settings | File Templates.
  }

  @Override
  public ToolListView<IEquipmentStats> initStatsListView(
          TechnologyAgnosticUIConfiguration<IEquipmentStats> configuration) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
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
