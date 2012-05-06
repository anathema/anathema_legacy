package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.platform.tree.view.PolygonPanel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.awt.event.MouseWheelEvent;

import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WheelScalerTest {

  PolygonPanel panel = mock(PolygonPanel.class);
  MouseWheelEvent event = mock(MouseWheelEvent.class);

  @Before
  public void setUp() throws Exception {
    when(event.getX()).thenReturn(100);
    when(event.getY()).thenReturn(120);
    when(event.getUnitsToScroll()).thenReturn(5);
  }

  @Test
  public void triggersScaleOnPanelCenteredOnEvent() throws Exception {
    new WheelScaler(panel).mouseWheelMoved(event);
    InOrder inOrder = inOrder(panel);
    inOrder.verify(panel).translate(100, 120);
    inOrder.verify(panel).scale(anyDouble());
    inOrder.verify(panel).translate(-100, -120);
  }

  @Test
  public void calculatesScaleFromNumberOfUnits() throws Exception {
    new WheelScaler(panel).mouseWheelMoved(event);
    verify(panel).scale(0.5);
  }

  @Test
  public void repaintsAfterTranslation() throws Exception {
    new WheelScaler(panel).mouseWheelMoved(event);
    InOrder inOrder = inOrder(panel);
    inOrder.verify(panel).translate(-100, -120);
    inOrder.verify(panel).repaint();
  }
}