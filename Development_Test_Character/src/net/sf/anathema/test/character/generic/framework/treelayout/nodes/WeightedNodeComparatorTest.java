package net.sf.anathema.test.character.generic.framework.treelayout.nodes;

import java.util.Arrays;

import net.sf.anathema.graph.nodes.WeightedNode;
import net.sf.anathema.graph.nodes.WeightedNodeComparator;
import net.sf.anathema.lib.testing.BasicTestCase;

public class WeightedNodeComparatorTest extends BasicTestCase {

  public void testLowerWeightFirst() throws Exception {
    WeightedNode node1 = new WeightedNode(null, new Double(1));
    WeightedNode node2 = new WeightedNode(null, new Double(2));
    WeightedNode[] nodes = new WeightedNode[] { node2, node1 };
    Arrays.sort(nodes, new WeightedNodeComparator());
    assertTrue(Arrays.equals(new WeightedNode[] { node1, node2 }, nodes));
  }

  public void testNullNotReorderedFirst() throws Exception {
    WeightedNode node1 = new WeightedNode(null, new Double(1));
    WeightedNode node2 = new WeightedNode(null, null);
    WeightedNode[] nodes = new WeightedNode[] { node2, node1 };
    Arrays.sort(nodes, new WeightedNodeComparator());
    assertTrue(Arrays.equals(new WeightedNode[] { node2, node1 }, nodes));
  }

  public void testNullNotReorderedLast() throws Exception {
    WeightedNode node1 = new WeightedNode(null, null);
    WeightedNode node2 = new WeightedNode(null, new Double(1));
    WeightedNode[] nodes = new WeightedNode[] { node2, node1 };
    Arrays.sort(nodes, new WeightedNodeComparator());
    assertTrue(Arrays.equals(new WeightedNode[] { node2, node1 }, nodes));
  }
}