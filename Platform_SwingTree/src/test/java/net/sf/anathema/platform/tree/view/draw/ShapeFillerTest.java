package net.sf.anathema.platform.tree.view.draw;

import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.platform.tree.display.shape.Polygon;
import net.sf.anathema.platform.tree.display.shape.TransformedShape;
import org.junit.Test;
import org.mockito.InOrder;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

public class ShapeFillerTest {

  @Test
  public void fillsPolygonWithColor() throws Exception {
    Canvas graphics = mock(Canvas.class);
    Polygon polygon = new Polygon();
    RGBColor color = RGBColor.White;
    new ShapeFiller(polygon, color).fill(graphics);
    InOrder inOrder = inOrder(graphics);
    inOrder.verify(graphics).setColor(color);
    inOrder.verify(graphics).fill(new TransformedShape(polygon));
  }
}
