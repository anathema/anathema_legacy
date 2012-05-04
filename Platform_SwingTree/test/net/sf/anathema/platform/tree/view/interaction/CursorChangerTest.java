package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.platform.tree.view.PolygonPanel;
import org.junit.Test;

import java.awt.event.MouseEvent;

import static net.sf.anathema.platform.tree.view.interaction.LeftClickTogglerTest.AnyPoint;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CursorChangerTest {
  PolygonPanel panel = mock(PolygonPanel.class);
  MouseEvent event = mock(MouseEvent.class);

  @Test
  public void triggersScaleOnPanel() throws Exception {
    when(event.getPoint()).thenReturn(AnyPoint);
    new CursorChanger(panel).mouseMoved(event);
    verify(panel).changeCursor(AnyPoint);
  }
}
