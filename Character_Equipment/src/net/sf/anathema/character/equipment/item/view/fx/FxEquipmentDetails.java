package net.sf.anathema.character.equipment.item.view.fx;

import javafx.application.Platform;
import javafx.scene.Node;
import net.sf.anathema.character.equipment.item.view.EquipmentDescriptionPanel;
import net.sf.anathema.character.equipment.item.view.EquipmentDetails;
import net.sf.anathema.character.equipment.item.view.ToolListView;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.lib.gui.TechnologyAgnosticUIConfiguration;
import org.tbee.javafx.scene.layout.MigPane;

public class FxEquipmentDetails implements EquipmentDetails {

  private MigPane outerPane;

  public FxEquipmentDetails() {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        outerPane = new MigPane();
      }
    });
  }

  public Node getNode() {
    return outerPane;
  }

  @Override
  public ToolListView<IEquipmentStats> initStatsListView(
          TechnologyAgnosticUIConfiguration<IEquipmentStats> configuration) {
    final FxToolListView<IEquipmentStats> listView = new FxToolListView<>();
    listView.setUiConfiguration(configuration);
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        outerPane.add(listView.getNode());
      }
    });
    return listView;
  }

  @Override
  public void setStatsListHeader(String headerText) {
    //nothing to do
  }

  @Override
  public EquipmentDescriptionPanel addDescriptionPanel(String title) {
    final FxEquipmentDescriptionPanel panel = new FxEquipmentDescriptionPanel();
    panel.setTitle(title);
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        outerPane.add(panel.getNode());
      }
    });
    return panel;
  }
}
