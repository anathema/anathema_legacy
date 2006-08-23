package net.sf.anathema.demo.character.equipment.item;

import net.sf.anathema.character.equipment.impl.item.model.EquipmentDatabase;
import net.sf.anathema.character.equipment.impl.item.view.EquimentDatabaseView;
import net.sf.anathema.character.equipment.item.EquipmentDatabasePresenter;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabase;
import net.sf.anathema.character.equipment.item.view.IEquipmentDatabaseView;
import net.sf.anathema.demo.platform.repository.tree.DemoResources;
import de.jdemo.extensions.SwingDemoCase;

public class EquipmentItemDataViewDemo extends SwingDemoCase {

  public void demoEmptyEquipmentItem() {
    IEquipmentDatabaseView view = new EquimentDatabaseView();
    IEquipmentDatabase model = new EquipmentDatabase();
    new EquipmentDatabasePresenter(new DemoResources(), model, view).initPresentation();
    show(view.getComponent());
  }
}