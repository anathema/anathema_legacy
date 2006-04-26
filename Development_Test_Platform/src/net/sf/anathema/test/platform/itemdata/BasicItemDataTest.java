package net.sf.anathema.test.platform.itemdata;

import net.sf.anathema.framework.itemdata.model.BasicItemData;
import net.sf.anathema.framework.itemdata.model.IBasicItemData;

public class BasicItemDataTest extends AbstractBasicItemDataTest {
  
  @Override
  public IBasicItemData getObjectUnderTest() {
    return new BasicItemData();
  }

}