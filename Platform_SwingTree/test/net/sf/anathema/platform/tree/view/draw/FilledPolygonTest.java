package net.sf.anathema.platform.tree.view.draw;

import net.disy.commons.swing.color.ColorUtilities;
import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class FilledPolygonTest {

  FilledPolygon filledPolygon = new FilledPolygon();

  @Before
  public void createSquare() throws Exception {
    filledPolygon.addPoint(0, 0);
    filledPolygon.addPoint(0, 2);
    filledPolygon.addPoint(2, 2);
    filledPolygon.addPoint(2, 0);
  }

  @Test
  public void containsPointOnPolygonBorder() throws Exception {
    assertThat(filledPolygon.contains(new Point(0, 0)), is(true));
  }

  @Test
  public void doesNotContainPointOutside() throws Exception {
    assertThat(filledPolygon.contains(new Point(3, 3)), is(false));
  }

  @Test
  public void containsPointInPolygon() throws Exception {
    assertThat(filledPolygon.contains(new Point(1, 1)), is(true));
  }

  @Test
  public void movesShape() throws Exception {
    filledPolygon.moveBy(3, 3);
    assertThat(filledPolygon.contains(new Point(4, 4)), is(true));
  }

  @Test
  public void fillsWithSetColor() throws Exception {
    Graphics2D graphics = mock(Graphics2D.class);
    filledPolygon.fill(Color.DARK_GRAY);
    filledPolygon.paint(graphics);
    verify(graphics).setColor(Color.DARK_GRAY);
  }

  @Test
  public void changesAlphaOfFill() throws Exception {
    Graphics2D graphics = mock(Graphics2D.class);
    filledPolygon.fill(Color.DARK_GRAY);
    filledPolygon.setAlpha(100);
    filledPolygon.paint(graphics);
    verify(graphics).setColor(ColorUtilities.getTransparentColor(Color.DARK_GRAY, 100));
  }

  @Test
  public void changesAlphaOfStroke() throws Exception {
    Graphics2D graphics = mock(Graphics2D.class);
    filledPolygon.setAlpha(17);
    filledPolygon.paint(graphics);
    verify(graphics).setColor(ColorUtilities.getTransparentColor(Color.BLACK, 17));
  }
}
