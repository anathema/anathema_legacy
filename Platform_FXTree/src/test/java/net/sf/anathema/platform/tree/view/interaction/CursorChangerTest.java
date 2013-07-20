package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.platform.tree.view.SwingPolygonPanel;
import org.junit.Test;

import java.awt.event.MouseEvent;

import static java.awt.Cursor.MOVE_CURSOR;
import static java.awt.Cursor.getPredefinedCursor;
import static net.sf.anathema.platform.tree.view.interaction.LeftClickTogglerTest.AnyPoint;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CursorChangerTest {
  SwingPolygonPanel panel = mock(SwingPolygonPanel.class);
  MouseEvent event = mock(MouseEvent.class);

  @Test
  public void changesCursorOnPanel() throws Exception {
    when(event.getPoint()).thenReturn(AnyPoint);
    new CursorChanger(panel).mouseMoved(event);
    verify(panel).changeCursor(AnyPoint);
  }

  @Test
  public void changesCursorDuringMouseDrag() throws Exception {
    when(event.getPoint()).thenReturn(AnyPoint);
    new CursorChanger(panel).mouseDragged(event);
    verify(panel).setCursor(getPredefinedCursor(MOVE_CURSOR));
  }
}