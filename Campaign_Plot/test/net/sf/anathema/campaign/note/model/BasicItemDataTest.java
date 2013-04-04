package net.sf.anathema.campaign.note.model;

import net.sf.anathema.framework.presenter.itemmanagement.PrintNameAdjuster;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.styledtext.model.ITextPart;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BasicItemDataTest {

  private IBasicItemData itemData = new BasicItemData();

  @Test
  public void hasNoContentAfterCreation() throws Exception {
    assertEquals("", itemData.getDescription().getName().getText());
    assertArrayEquals(new ITextPart[0], itemData.getDescription().getContent().getTextParts());
  }

  @Test
  public void usesAdjusterForPrintName() throws Exception {
    IItem item = mock(IItem.class);
    itemData.setPrintNameAdjuster(new PrintNameAdjuster(item));
    itemData.getDescription().getName().setText("New Name");
    verify(item).setPrintName("New Name");
  }
}