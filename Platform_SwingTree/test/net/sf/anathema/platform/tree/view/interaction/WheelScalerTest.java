package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.platform.tree.view.SwingPolygonPanel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.awt.event.MouseWheelEvent;

import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WheelScalerTest {

  SwingPolygonPanel panel = mock(SwingPolygonPanel.class);
  MouseWheelEvent event = mock(MouseWheelEvent.class);

  @Before
  public void setUp() throws Exception {
    when(event.getX()).thenReturn(100);
    when(event.getY()).thenReturn(120);
    when(event.getWheelRotation()).thenReturn(5);
  }

  @Test
  public void triggersScaleOnPanelCenteredOnEvent() throws Exception {
    when(event.getWheelRotation()).thenReturn(5);
    new WheelScaler(panel).mouseWheelMoved(event);
    InOrder inOrder = inOrder(panel);
    inOrder.verify(panel).scaleToPoint(anyDouble(), eq(100), eq(120));
  }

  @Test
  public void calculatesScaleFromNumberOfUnits() throws Exception {
    when(event.getWheelRotation()).thenReturn(5);
    new WheelScaler(panel).mouseWheelMoved(event);
    scalesToAnyPoint(0.75);
  }

  @Test
  public void doesNotGoToZero() throws Exception {
    when(event.getWheelRotation()).thenReturn(20);
    new WheelScaler(panel).mouseWheelMoved(event);
    scalesToAnyPoint(0.00001);
  }

  @Test
  public void limitsChangeToOne100Percent() throws Exception {
    when(event.getWheelRotation()).thenReturn(-21);
    new WheelScaler(panel).mouseWheelMoved(event);
    scalesToAnyPoint(2);
  }

  @Test
  public void repaintsAfterTranslation() throws Exception {
    when(event.getWheelRotation()).thenReturn(5);
    new WheelScaler(panel).mouseWheelMoved(event);
    InOrder inOrder = inOrder(panel);
    inOrder.verify(panel).scaleToPoint(anyDouble(), anyInt(), anyInt());
    inOrder.verify(panel).repaint();
  }

  private void scalesToAnyPoint(double scale) {
    verify(panel).scaleToPoint(eq(scale), anyInt(), anyInt());
  }
}