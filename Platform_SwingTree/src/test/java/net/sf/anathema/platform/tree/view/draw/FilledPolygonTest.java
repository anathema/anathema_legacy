package net.sf.anathema.platform.tree.view.draw;

import org.junit.Before;
import org.junit.Test;

import java.awt.Point;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
}
