package net.sf.anathema.platform.tree.display.shape;

import net.sf.anathema.framework.ui.Coordinate;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PolygonTest {

  Polygon polygon = new Polygon();

  @Before
  public void setUp() throws Exception {
    polygon.addPoint(5, 5);
    polygon.addPoint(5, 10);
    polygon.addPoint(10, 10);
    polygon.addPoint(10, 5);
  }

  @Test
  public void doesNotContainPointBeyond() throws Exception {
    boolean contains = polygon.contains(new Coordinate(15, 15));
    assertThat(contains, is(false));
  }

  @Test
  public void containsPointInside() throws Exception {
    boolean contains = polygon.contains(new Coordinate(7, 7));
    assertThat(contains, is(true));
  }

  @Test
  public void doesNotContainPointBefore() throws Exception {
    boolean contains = polygon.contains(new Coordinate(2, 2));
    assertThat(contains, is(false));
  }
}
