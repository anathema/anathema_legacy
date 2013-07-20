package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.framework.ui.Coordinate;
import org.junit.Test;
import org.mockito.InOrder;

import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class WheelScalerTest {

  PolygonPanel panel = mock(PolygonPanel.class);

  @Test
  public void triggersScaleOnPanelCenteredOnEvent() throws Exception {
    new WheelScaler(panel).mouseWheelMoved(5, new Coordinate(100, 120));
    InOrder inOrder = inOrder(panel);
    inOrder.verify(panel).scaleToPoint(anyDouble(), new Coordinate(100, 120));
  }

  @Test
  public void calculatesScaleFromNumberOfUnits() throws Exception {
    new WheelScaler(panel).mouseWheelMoved(5, new Coordinate(100, 120));
    scalesToAnyPoint(0.75);
  }

  @Test
  public void doesNotGoToZero() throws Exception {
    new WheelScaler(panel).mouseWheelMoved(20, new Coordinate(100, 120));
    scalesToAnyPoint(0.00001);
  }

  @Test
  public void limitsChangeToOne100Percent() throws Exception {
    new WheelScaler(panel).mouseWheelMoved(-21, new Coordinate(100, 120));
    scalesToAnyPoint(2);
  }

  @Test
  public void repaintsAfterTranslation() throws Exception {
    new WheelScaler(panel).mouseWheelMoved(5, new Coordinate(100, 120));
    InOrder inOrder = inOrder(panel);
    inOrder.verify(panel).scaleToPoint(anyDouble(), new Coordinate(anyInt(), anyInt()));
    inOrder.verify(panel).refresh();
  }

  private void scalesToAnyPoint(double scale) {
    verify(panel).scaleToPoint(eq(scale), eq(new Coordinate(anyInt(), anyInt())));
  }
}