package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.platform.tree.view.PolygonPanel;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.awt.event.MouseEvent;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class RightClickResetterTest {

  private PolygonPanel panel = mock(PolygonPanel.class);
  private MouseEvent event = mock(MouseEvent.class);
  private RightClickResetter resetter = new RightClickResetter(panel);

  @Test
  public void resetsTransformationAndZoomsOutALittleOnRightDoubleClick() throws Exception {
    when(event.getModifiers()).thenReturn(MouseEvent.BUTTON3_MASK);
    when(event.getClickCount()).thenReturn(2);
    resetter.mouseClicked(event);
    InOrder inOrder = Mockito.inOrder(panel);
    inOrder.verify(panel).resetTransformation();
    inOrder.verify(panel).scale(0.75);
  }

  @Test
  public void doesNothingOnSingleClick() throws Exception {
    when(event.getModifiers()).thenReturn(MouseEvent.BUTTON3_MASK);
    when(event.getClickCount()).thenReturn(1);
    resetter.mouseClicked(event);
    verifyZeroInteractions(panel);
  }

  @Test
  public void doesNothingOnLeftClick() throws Exception {
    when(event.getModifiers()).thenReturn(MouseEvent.BUTTON1_MASK);
    when(event.getClickCount()).thenReturn(2);
    resetter.mouseClicked(event);
    verifyZeroInteractions(panel);
  }
}
