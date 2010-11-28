package net.sf.anathema.test.character.generic.framework.treelayout.util;

import net.sf.anathema.graph.util.BarycenterCalculator;
import net.sf.anathema.lib.testing.BasicTestCase;

public class BarycenterCalculatorTest extends BasicTestCase {

  public void testUnconnectedNode() throws Exception {
    boolean[] connections = new boolean[] { false };
    Double center = BarycenterCalculator.calculateVectorCenter(connections);
    assertEquals(null, center);
  }

  public void testConnectedNode() throws Exception {
    boolean[] connections = new boolean[] { true };
    Double center = BarycenterCalculator.calculateVectorCenter(connections);
    assertEquals(1.0, center);
  }

  public void testOrderDependent() throws Exception {
    boolean[] connections = new boolean[] { true, false };
    Double center = BarycenterCalculator.calculateVectorCenter(connections);
    assertEquals(1.0, center);
    boolean[] connections2 = new boolean[] { false, true };
    Double center2 = BarycenterCalculator.calculateVectorCenter(connections2);
    assertEquals(2.0, center2);
  }
}