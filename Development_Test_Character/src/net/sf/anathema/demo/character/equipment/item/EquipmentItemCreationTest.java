package net.sf.anathema.demo.character.equipment.item;

import net.sf.anathema.character.equipment.impl.item.model.EquipmentDatabase;
import net.sf.anathema.framework.itemdata.model.IBasicItemData;
import net.sf.anathema.test.platform.itemdata.AbstractBasicItemDataTest;

public class EquipmentItemCreationTest extends AbstractBasicItemDataTest {

  @Override
  public IBasicItemData getObjectUnderTest() {
    return new EquipmentDatabase();
  }
}