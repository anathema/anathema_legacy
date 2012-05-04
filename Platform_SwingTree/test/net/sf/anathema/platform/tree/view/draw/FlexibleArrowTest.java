package net.sf.anathema.platform.tree.view.draw;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FlexibleArrowTest {

  FlexibleArrow arrow = new FlexibleArrow();
  Graphics2D graphics = mock(Graphics2D.class);

  @Before
  public void setUp() throws Exception {
    arrow.addPoint(17, 6);
    arrow.addPoint(6, 10);
  }

  @Test
  public void connectsDots() throws Exception {
    arrow.paint(graphics);
    verify(graphics).drawPolyline(new int[]{17, 6}, new int[]{6, 10}, 2);
  }

  @Test
  public void drawsWide() throws Exception {
    arrow.paint(graphics);
    InOrder inOrder = inOrder(graphics);
    inOrder.verify(graphics).setStroke(new BasicStroke(6));
    inOrder.verify(graphics).drawPolyline(any(int[].class), any(int[].class), anyInt());
  }

  @Test
  public void drawsBlack() throws Exception {
    arrow.paint(graphics);
    InOrder inOrder = inOrder(graphics);
    inOrder.verify(graphics).setColor(Color.BLACK);
    inOrder.verify(graphics).drawPolyline(any(int[].class), any(int[].class), anyInt());
  }

  @Test
  public void hasDotAtBottom() throws Exception {
    arrow.paint(graphics);
    Ellipse2D.Float circle = new Ellipse2D.Float(11, 0, 12, 12);
    verify(graphics).fill(circle);
  }

  @Test
  public void hasRotatedArrowAtHead() throws Exception {

  }
}