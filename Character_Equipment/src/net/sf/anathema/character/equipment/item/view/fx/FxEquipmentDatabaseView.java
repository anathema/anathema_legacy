package net.sf.anathema.character.equipment.item.view.fx;

import javafx.application.Platform;
import net.sf.anathema.character.equipment.item.view.AgnosticEquipmentDatabaseView;
import net.sf.anathema.platform.fx.FxThreading;
import net.sf.anathema.platform.fx.PerspectivePane;

public class FxEquipmentDatabaseView {

  public final PerspectivePane perspectivePane = new PerspectivePane("skin/anathema/equipment.css");
  private final FxEquipmentDetails details = new FxEquipmentDetails();
  private final FxEquipmentNavigation navigation = new FxEquipmentNavigation();
  public final AgnosticEquipmentDatabaseView view = new AgnosticEquipmentDatabaseView(navigation, details);

  public FxEquipmentDatabaseView() {
    FxThreading.assertNotOnFxThread();
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        perspectivePane.addStyleSheetClass("equipment-perspective");
        perspectivePane.setNavigationComponent(navigation.getNode());
        perspectivePane.setContentComponent(details.getNode());
      }
    });
  }
}