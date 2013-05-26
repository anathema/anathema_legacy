package net.sf.anathema.character.equipment.item.view.fx;

import javafx.application.Platform;
import javafx.scene.Node;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.sf.anathema.character.equipment.item.view.EquipmentDescriptionPanel;
import net.sf.anathema.character.equipment.item.view.EquipmentDetails;
import net.sf.anathema.character.equipment.item.view.ToolListView;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.platform.fx.BorderedTitledPane;
import org.tbee.javafx.scene.layout.MigPane;

public class FxEquipmentDetails implements EquipmentDetails {

  private final FxToolListView<IEquipmentStats> listView = new FxToolListView<>();
  private final FxEquipmentDescriptionPanel descriptionPanel = new FxEquipmentDescriptionPanel();
  private MigPane outerPane;

  public FxEquipmentDetails() {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        outerPane = new MigPane(LayoutUtils.fillWithoutInsets().wrapAfter(1), new AC().grow().fill());
      }
    });
  }

  public Node getNode() {
    return outerPane;
  }

  @Override
  public ToolListView<IEquipmentStats> initStatsListView(final String title,
                                                         AgnosticUIConfiguration<IEquipmentStats> configuration) {
    listView.setUiConfiguration(configuration);
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        Node node = listView.getNode();
        BorderedTitledPane titledPane = BorderedTitledPane.Create(title, node);
        outerPane.add(titledPane, new CC().push().grow());
      }
    });
    return listView;
  }

  @Override
  public EquipmentDescriptionPanel addDescriptionPanel(final String title) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        BorderedTitledPane titledPane = BorderedTitledPane.Create(title, descriptionPanel.getNode());
        outerPane.add(titledPane, new CC().grow().push());
      }
    });
    return descriptionPanel;
  }
}