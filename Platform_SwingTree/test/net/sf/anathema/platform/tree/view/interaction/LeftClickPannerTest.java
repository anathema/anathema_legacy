package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.platform.tree.view.PolygonPanel;
import org.junit.Before;
import org.junit.Test;

import java.awt.Point;
import java.awt.event.MouseEvent;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
public class LeftClickPannerTest {

  PolygonPanel panel = mock(PolygonPanel.class);
  MouseEvent pressEvent = mock(MouseEvent.class);
  MouseEvent dragEvent = mock(MouseEvent.class);
  LeftClickPanner panner = new LeftClickPanner(panel);

  @Before
  public void setUp() throws Exception {
    when(pressEvent.getPoint()).thenReturn(new Point(10, 10));
    when(dragEvent.getPoint()).thenReturn(new Point(15, 15));
    when(dragEvent.getModifiers()).thenReturn(MouseEvent.BUTTON1_MASK);
  }

  @Test
  public void activatesOnLeftButtonOnly() throws Exception {
    when(dragEvent.getModifiers()).thenReturn(MouseEvent.BUTTON3_MASK);
    panner.mousePressed(pressEvent);
    panner.mouseDragged(dragEvent);
    verifyZeroInteractions(panel);
  }

  @Test
  public void translatesByDifferenceMoved() throws Exception {
    panner.mousePressed(pressEvent);
    panner.mouseDragged(dragEvent);
    verify(panel).translate(5, 5);
  }

  @Test
  public void translatesByDifferenceFromLastDrag() throws Exception {
    when(dragEvent.getPoint()).thenReturn(new Point(15, 15), new Point(25, 25));
    panner.mousePressed(pressEvent);
    panner.mouseDragged(dragEvent);
    panner.mouseDragged(dragEvent);
    verify(panel).translate(10, 10);
  }

  @Test
  public void repaintsAfterTranslation() throws Exception {
    panner.mousePressed(pressEvent);
    panner.mouseDragged(dragEvent);
    verify(panel).translate(anyInt(), anyInt());
    verify(panel).repaint();
  }
}