package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.platform.tree.view.PolygonPanel;
import org.junit.Before;
import org.junit.Test;

import java.awt.Point;
import java.awt.event.MouseEvent;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class RightClickCentererTest {

  PolygonPanel panel = mock(PolygonPanel.class);
  MouseEvent event = mock(MouseEvent.class);
  RightClickCenterer centerer = new RightClickCenterer(panel);

  @Before
  public void setCoordinates() throws Exception {
    when(event.getPoint()).thenReturn(new Point(10, 10));
  }

  @Test
  public void centersPanelOnCursorPositionOnRightClick() throws Exception {
    when(event.getModifiers()).thenReturn(MouseEvent.BUTTON3_MASK);
    when(event.getClickCount()).thenReturn(1);
    centerer.mouseClicked(event);
    verify(panel).centerOn(10, 10);
  }

  @Test
  public void doesNotReactOnLeftClick() throws Exception {
    when(event.getModifiers()).thenReturn(MouseEvent.BUTTON1_MASK);
    when(event.getClickCount()).thenReturn(1);
    centerer.mouseClicked(event);
    verifyZeroInteractions(panel);
  }


  @Test
  public void doesNotReactOnDoubleClick() throws Exception {
    when(event.getModifiers()).thenReturn(MouseEvent.BUTTON3_MASK);
    when(event.getClickCount()).thenReturn(2);
    centerer.mouseClicked(event);
    verifyZeroInteractions(panel);
  }
}