package net.sf.anathema.demo.character.equipment.item;

import net.sf.anathema.character.equipment.impl.item.model.EquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.impl.item.view.EquipmentDatabaseView;
import net.sf.anathema.character.equipment.item.EquipmentDatabasePresenter;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.view.IEquipmentDatabaseView;
import net.sf.anathema.demo.platform.repository.tree.DemoResources;
import de.jdemo.extensions.SwingDemoCase;

public class EquipmentDatabaseViewDemo extends SwingDemoCase {

  public void demoEmptyEquipmentItem() {
    IEquipmentDatabaseView view = new EquipmentDatabaseView();
    DemoEquipmentDatabase equipmentDatabase = new DemoEquipmentDatabase();
    IEquipmentDatabaseManagement model = new EquipmentDatabaseManagement(equipmentDatabase);
    new EquipmentDatabasePresenter(new DemoResources(), model, view).initPresentation();
    show(view.getComponent());
  }
}