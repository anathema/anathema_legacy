package net.sf.anathema.character.equipment.item.view.swing;

import net.sf.anathema.character.equipment.item.view.AgnosticEquipmentDatabaseView;
import net.sf.anathema.character.equipment.item.view.EquipmentDatabaseView;
import net.sf.anathema.framework.perspective.SwingPerspectivePane;

public class SwingEquipmentDatabaseView  {
  private final SwingEquipmentNavigation navigation = new SwingEquipmentNavigation();
  private final SwingEquipmentDetails details = new SwingEquipmentDetails();
  public final SwingPerspectivePane perspectivePane = new SwingPerspectivePane();
  public final EquipmentDatabaseView view = new AgnosticEquipmentDatabaseView(navigation, details);

  public SwingEquipmentDatabaseView() {
    perspectivePane.setNavigationComponent(navigation.getComponent());
    perspectivePane.setContentComponent(details.getComponent());
  }
}