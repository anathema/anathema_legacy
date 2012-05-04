package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.platform.tree.view.PolygonPanel;
import org.junit.Test;

import java.awt.event.MouseWheelEvent;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WheelScalerTest {

  private static final int AnyNumberOfUnits = 5;
  PolygonPanel panel = mock(PolygonPanel.class);
  MouseWheelEvent event = mock(MouseWheelEvent.class);

  @Test
  public void triggersScaleOnPanel() throws Exception {
    when(event.getUnitsToScroll()).thenReturn(AnyNumberOfUnits);
    new WheelScaler(panel).mouseWheelMoved(event);
    verify(panel).scale(AnyNumberOfUnits);
  }
}
