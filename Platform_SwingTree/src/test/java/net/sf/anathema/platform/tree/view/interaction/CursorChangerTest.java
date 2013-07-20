package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.platform.tree.view.SwingPolygonPanel;
import org.junit.Test;

import static net.sf.anathema.platform.tree.view.interaction.LeftClickTogglerTest.AnyCoordinate;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CursorChangerTest {
  SwingPolygonPanel panel = mock(SwingPolygonPanel.class);

  @Test
  public void changesCursorOnPanel() throws Exception {
    new CursorChanger(panel).mouseMoved(AnyCoordinate);
    verify(panel).changeCursor(AnyCoordinate);
  }

  @Test
  public void changesCursorDuringMouseDrag() throws Exception {
    new CursorChanger(panel).mouseDragged(MouseButton.Left, AnyCoordinate);
    verify(panel).showMoveCursor();
  }
}