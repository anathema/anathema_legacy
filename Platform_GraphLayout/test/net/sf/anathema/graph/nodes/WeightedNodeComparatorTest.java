package net.sf.anathema.graph.nodes;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class WeightedNodeComparatorTest {

  @Test
  public void testLowerWeightFirst() throws Exception {
    WeightedNode node1 = new WeightedNode(null, (double) 1);
    WeightedNode node2 = new WeightedNode(null, (double) 2);
    WeightedNode[] nodes = new WeightedNode[]{node2, node1};
    Arrays.sort(nodes, new WeightedNodeComparator());
    assertTrue(Arrays.equals(new WeightedNode[]{node1, node2}, nodes));
  }

  @Test
  public void testNullNotReorderedFirst() throws Exception {
    WeightedNode node1 = new WeightedNode(null, (double) 1);
    WeightedNode node2 = new WeightedNode(null, null);
    WeightedNode[] nodes = new WeightedNode[]{node2, node1};
    Arrays.sort(nodes, new WeightedNodeComparator());
    assertTrue(Arrays.equals(new WeightedNode[]{node2, node1}, nodes));
  }

  @Test
  public void testNullNotReorderedLast() throws Exception {
    WeightedNode node1 = new WeightedNode(null, null);
    WeightedNode node2 = new WeightedNode(null, (double) 1);
    WeightedNode[] nodes = new WeightedNode[]{node2, node1};
    Arrays.sort(nodes, new WeightedNodeComparator());
    assertTrue(Arrays.equals(new WeightedNode[]{node2, node1}, nodes));
  }
}