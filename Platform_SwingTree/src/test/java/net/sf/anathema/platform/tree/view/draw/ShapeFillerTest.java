package net.sf.anathema.platform.tree.view.draw;

import net.sf.anathema.framework.ui.RGBColor;
import org.junit.Test;
import org.mockito.InOrder;

import java.awt.Polygon;
import java.awt.Shape;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

public class ShapeFillerTest {

  @Test
  public void fillsPolygonWithColor() throws Exception {
    Canvas graphics = mock(Canvas.class);
    Shape polygon = new Polygon();
    RGBColor color = RGBColor.White;
    new ShapeFiller(polygon, color).fill(graphics);
    InOrder inOrder = inOrder(graphics);
    inOrder.verify(graphics).setColor(color);
    inOrder.verify(graphics).fill(polygon);
  }
}
