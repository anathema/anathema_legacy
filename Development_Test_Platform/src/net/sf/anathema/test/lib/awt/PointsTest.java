package net.sf.anathema.test.lib.awt;

import java.awt.Point;

import net.sf.anathema.lib.awt.Points;
import net.sf.anathema.lib.testing.BasicTestCase;



public class PointsTest extends BasicTestCase {

  public void testDistance() {
    assertEquals(1.414, Points.getDistance(new Point(0, 0), new Point(1, 1)), 0.001);
  }

  public void testNoDistance() {
    assertEquals(0.0, Points.getDistance(new Point(0, 0), new Point(0, 0)), 0.0);
  }
  
  public void testNeighbors() throws Exception {
    assertTrue(Points.neighbors(new Point(1,1), new Point(2, 2)));
    assertTrue(Points.neighbors(new Point(1,1), new Point(1, 2)));
    assertFalse(Points.neighbors(new Point(1,1), new Point(1, 1)));
    assertFalse(Points.neighbors(new Point(1,1), new Point(1, 3)));
    assertFalse(Points.neighbors(new Point(1,1), new Point(3, 3)));
  }
}