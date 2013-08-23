package net.sf.anathema.character.equipment.item.view.fx;

import net.sf.anathema.character.equipment.item.view.AgnosticEquipmentDatabaseView;
import net.sf.anathema.platform.fx.PerspectivePane;
import net.sf.anathema.platform.fx.selection.ComboBoxSelectionFactory;

import static net.sf.anathema.platform.fx.FxThreading.runOnCorrectThread;

public class FxEquipmentDatabaseView {

  public final PerspectivePane perspectivePane = new PerspectivePane("skin/equipment/equipment.css", "skin/platform/dotselector.css");
  private final FxEquipmentNavigation navigation = new FxEquipmentNavigation();
  private final FxEquipmentDetails details;
  public final AgnosticEquipmentDatabaseView view;

  public FxEquipmentDatabaseView() {
    this.details = new FxEquipmentDetails(new ComboBoxSelectionFactory());
    this.view = new AgnosticEquipmentDatabaseView(navigation, details);
    initializePerspective();
  }

  private void initializePerspective() {
    runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        perspectivePane.addStyleSheetClass("equipment-perspective");
        perspectivePane.setNavigationComponent(navigation.getNode());
        perspectivePane.setContentComponent(details.getNode());
      }
    });
  }
}