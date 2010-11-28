package net.sf.anathema.test.platform.itemdata;

import net.sf.anathema.framework.itemdata.model.IBasicItemData;
import net.sf.anathema.framework.presenter.itemmanagement.PrintNameAdjuster;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.styledtext.model.ITextPart;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractBasicItemDataTest {

  public abstract IBasicItemData getObjectUnderTest();

  @Test
  public void testBasicDataOnCreation() throws Exception {
    IBasicItemData itemData = getObjectUnderTest();
    Assert.assertEquals("", itemData.getDescription().getName().getText()); //$NON-NLS-1$
    Assert.assertArrayEquals(new ITextPart[0], itemData.getDescription().getContent().getTextParts());
  }

  @Test
  public void testPrintNameAdjustment() throws Exception {
    IItem item = EasyMock.createMock(IItem.class);
    item.setPrintName("Neuer Name"); //$NON-NLS-1$
    EasyMock.replay();
    IBasicItemData itemData = getObjectUnderTest();
    itemData.setPrintNameAdjuster(new PrintNameAdjuster(item));
    itemData.getDescription().getName().setText("Neuer Name"); //$NON-NLS-1$
    EasyMock.verify();
  }
}