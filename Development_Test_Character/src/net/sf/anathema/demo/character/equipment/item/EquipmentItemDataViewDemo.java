package net.sf.anathema.demo.character.equipment.item;

import net.sf.anathema.character.equipment.impl.item.model.EquipmentItemData;
import net.sf.anathema.character.equipment.impl.item.view.EquimentItemDataView;
import net.sf.anathema.character.equipment.item.EquipmentItemDataPresenter;
import net.sf.anathema.character.equipment.item.model.IEquipmentItemData;
import net.sf.anathema.character.equipment.item.view.IEquipmentItemDataView;
import net.sf.anathema.demo.platform.repository.tree.DemoResources;
import de.jdemo.extensions.SwingDemoCase;

public class EquipmentItemDataViewDemo extends SwingDemoCase {

  public void demoEmptyEquipmentItem() {
    IEquipmentItemDataView view = new EquimentItemDataView();
    IEquipmentItemData model = new EquipmentItemData();
    new EquipmentItemDataPresenter(new DemoResources(), model, view).initPresentation();
    show(view.getComponent());
  }
}