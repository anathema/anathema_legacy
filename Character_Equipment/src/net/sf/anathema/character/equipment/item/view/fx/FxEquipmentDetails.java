package net.sf.anathema.character.equipment.item.view.fx;

import javafx.application.Platform;
import javafx.scene.Node;
import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.equipment.item.view.EquipmentDescriptionPanel;
import net.sf.anathema.character.equipment.item.view.EquipmentDetails;
import net.sf.anathema.character.equipment.item.view.ToolListView;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import org.tbee.javafx.scene.layout.MigPane;

public class FxEquipmentDetails implements EquipmentDetails {

  private final FxToolListView<IEquipmentStats> listView = new FxToolListView<>();
  private MigPane outerPane;

  public FxEquipmentDetails() {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        outerPane = new MigPane(LayoutUtils.fillWithoutInsets().wrapAfter(1));
      }
    });
  }

  public Node getNode() {
    return outerPane;
  }

  @Override
  public ToolListView<IEquipmentStats> initStatsListView(
          AgnosticUIConfiguration<IEquipmentStats> configuration) {
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
    listView.setHeader(headerText);
  }

  @Override
  public EquipmentDescriptionPanel addDescriptionPanel(String title) {
    final FxEquipmentDescriptionPanel panel = new FxEquipmentDescriptionPanel();
    panel.setTitle(title);
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        outerPane.add(panel.getNode(), new CC().grow().pushX());
      }
    });
    return panel;
  }
}