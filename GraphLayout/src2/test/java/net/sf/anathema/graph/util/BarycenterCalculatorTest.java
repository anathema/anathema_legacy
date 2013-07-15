package net.sf.anathema.graph.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BarycenterCalculatorTest {

  @Test
  public void testUnconnectedNode() throws Exception {
    boolean[] connections = new boolean[]{false};
    Double center = BarycenterCalculator.calculateVectorCenter(connections);
    assertEquals(null, center);
  }

  @Test
  public void testConnectedNode() throws Exception {
    boolean[] connections = new boolean[]{true};
    Double center = BarycenterCalculator.calculateVectorCenter(connections);
    assertEquals(new Double(1), center);
  }

  @Test
  public void testOrderDependent() throws Exception {
    boolean[] connections = new boolean[]{true, false};
    Double center = BarycenterCalculator.calculateVectorCenter(connections);
    assertEquals(new Double(1), center);
    boolean[] connections2 = new boolean[]{false, true};
    Double center2 = BarycenterCalculator.calculateVectorCenter(connections2);
    assertEquals(new Double(2), center2);
  }
}