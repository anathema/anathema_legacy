package net.sf.anathema.test.platform.itemdata;

import net.sf.anathema.framework.itemdata.model.IBasicItemData;
import net.sf.anathema.framework.presenter.itemmanagement.PrintNameAdjuster;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.styledtext.model.ITextPart;
import net.sf.anathema.lib.testing.BasicTestCase;

import org.easymock.MockControl;

public abstract class AbstractBasicItemDataTest extends BasicTestCase {

  public abstract IBasicItemData getObjectUnderTest();

  public void testBasicDataOnCreation() throws Exception {
    IBasicItemData itemData = getObjectUnderTest();
    assertEquals("", itemData.getDescription().getName().getText()); //$NON-NLS-1$
    assertEquals(new ITextPart[0], itemData.getDescription().getContent().getTextParts());
  }

  public void testPrintNameAdjustment() throws Exception {
    MockControl itemControl = MockControl.createControl(IItem.class);
    IItem item = (IItem) itemControl.getMock();
    item.setPrintName("Neuer Name"); //$NON-NLS-1$
    itemControl.replay();
    IBasicItemData itemData = getObjectUnderTest();
    itemData.setPrintNameAdjuster(new PrintNameAdjuster(item));
    itemData.getDescription().getName().setText("Neuer Name"); //$NON-NLS-1$
    itemControl.verify();
  }
}