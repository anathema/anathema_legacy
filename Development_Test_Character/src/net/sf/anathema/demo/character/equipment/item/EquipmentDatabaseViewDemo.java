package net.sf.anathema.demo.character.equipment.item;

import java.io.File;

import net.sf.anathema.character.equipment.impl.item.model.EquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.impl.item.model.db4o.Db4OEquipmentDatabase;
import net.sf.anathema.character.equipment.impl.item.view.EquipmentDatabaseView;
import net.sf.anathema.character.equipment.item.EquipmentDatabasePresenter;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabase;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.view.IEquipmentDatabaseView;
import net.sf.anathema.demo.platform.repository.tree.DemoResources;
import de.jdemo.extensions.SwingDemoCase;

public class EquipmentDatabaseViewDemo extends SwingDemoCase {

  public void demoWithDemoDataBase() {
    showForDatabase(new DemoEquipmentDatabase());
  }

  public void demoWithDb4oDataBase() {
    showForDatabase(new Db4OEquipmentDatabase(new File("DemoEquipment.yap")));
  }

  public void demoWithProductionDataBase() {
    showForDatabase(new Db4OEquipmentDatabase(Db4OEquipmentDatabase.DATABASE_FILE));
  }

  private void showForDatabase(IEquipmentDatabase equipmentDatabase) {
    IEquipmentDatabaseView view = new EquipmentDatabaseView();
    IEquipmentDatabaseManagement model = new EquipmentDatabaseManagement(equipmentDatabase);
    new EquipmentDatabasePresenter(new DemoResources(), model, view).initPresentation();
    show(view.getComponent());
  }
}