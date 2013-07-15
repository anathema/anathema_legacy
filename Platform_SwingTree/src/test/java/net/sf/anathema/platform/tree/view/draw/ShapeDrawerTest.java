package net.sf.anathema.platform.tree.view.draw;

import org.junit.Test;
import org.mockito.InOrder;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

public class ShapeDrawerTest {

  private Color stroke = Color.CYAN;

  @Test
  public void drawsPolygonOutline() throws Exception {
    Graphics2D graphics = mock(Graphics2D.class);
    Shape polygon = new Polygon();
    new ShapeDrawer(polygon, stroke).draw(graphics);
    InOrder inOrder = inOrder(graphics);
    inOrder.verify(graphics).setColor(Color.CYAN);
    inOrder.verify(graphics).setStroke(new BasicStroke(4));
    inOrder.verify(graphics).draw(polygon);
  }
}
