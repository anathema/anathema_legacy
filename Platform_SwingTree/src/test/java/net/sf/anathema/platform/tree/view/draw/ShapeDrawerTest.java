package net.sf.anathema.platform.tree.view.draw;

import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.framework.ui.Width;
import net.sf.anathema.platform.tree.display.shape.Polygon;
import org.junit.Test;
import org.mockito.InOrder;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

public class ShapeDrawerTest {

  private RGBColor stroke = RGBColor.Pink;

  @Test
  public void drawsPolygonOutline() throws Exception {
    Canvas graphics = mock(Canvas.class);
    Polygon polygon = new Polygon();
    new ShapeDrawer(polygon, stroke).draw(graphics);
    InOrder inOrder = inOrder(graphics);
    inOrder.verify(graphics).setColor(RGBColor.Pink);
    inOrder.verify(graphics).setStrokeWidth(new Width(4));
    inOrder.verify(graphics).draw(polygon);
  }
}
