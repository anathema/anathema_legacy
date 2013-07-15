package net.sf.anathema.platform.tree.view.draw;

import org.junit.Test;
import org.mockito.InOrder;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

public class ShapeFillerTest {

  @Test
  public void fillsPolygonWithColor() throws Exception {
    Graphics2D graphics = mock(Graphics2D.class);
    Shape polygon = new Polygon();
    Color color = Color.WHITE;
    new ShapeFiller(polygon, color).fill(graphics);
    InOrder inOrder = inOrder(graphics);
    inOrder.verify(graphics).setColor(color);
    inOrder.verify(graphics).fill(polygon);
  }
}
