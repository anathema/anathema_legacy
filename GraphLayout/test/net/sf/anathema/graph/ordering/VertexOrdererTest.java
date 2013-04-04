package net.sf.anathema.graph.ordering;

import net.sf.anathema.graph.graph.IProperHierarchicalGraph;
import net.sf.anathema.graph.graph.ProperHierarchicalGraph;
import net.sf.anathema.graph.nodes.ISimpleNode;
import net.sf.anathema.graph.nodes.NodeFactory;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VertexOrdererTest {

  @Test
  public void testNoCrossingsUnchanged() throws Exception {
    ISimpleNode leaf1 = NodeFactory.createChildlessNode(2, "leaf1");
    ISimpleNode root1 = NodeFactory.createSingleChildNode(1, leaf1, "root1");
    ISimpleNode leaf2 = NodeFactory.createChildlessNode(2, "leaf2");
    ISimpleNode root2 = NodeFactory.createSingleChildNode(1, leaf2, "root2");
    IProperHierarchicalGraph graph = new ProperHierarchicalGraph(new ISimpleNode[]{root1, root2, leaf1, leaf2}, 2);
    orderGraph(graph);
    assertTrue(Arrays.equals(new ISimpleNode[]{root1, root2}, graph.getNodesByLayer(1)));
    assertTrue(Arrays.equals(new ISimpleNode[]{leaf1, leaf2}, graph.getNodesByLayer(2)));
  }

  @Test
  public void testOneCrossingSolved() throws Exception {
    ISimpleNode leaf1 = NodeFactory.createChildlessNode(2, "leaf1");
    ISimpleNode root1 = NodeFactory.createSingleChildNode(1, leaf1, "root1");
    ISimpleNode leaf2 = NodeFactory.createChildlessNode(2, "leaf2");
    ISimpleNode root2 = NodeFactory.createSingleChildNode(1, leaf2, "root2");
    ISimpleNode[] simpleNodes = new ISimpleNode[]{root2, root1, leaf1, leaf2};
    IProperHierarchicalGraph graph = new ProperHierarchicalGraph(simpleNodes, 2);
    orderGraph(graph);
    assertTrue(Arrays.equals(new ISimpleNode[]{root2, root1}, graph.getNodesByLayer(1)));
    assertTrue(Arrays.equals(new ISimpleNode[]{leaf2, leaf1}, graph.getNodesByLayer(2)));
  }

  /**
   * Setup and solution from Sugiyama, page 74
   */
  @Test
  public void testSugiyamaFourLayerExample() throws Exception {
    ISimpleNode j = NodeFactory.createChildlessNode(4, "j");
    ISimpleNode k = NodeFactory.createChildlessNode(4, "k");
    ISimpleNode l = NodeFactory.createChildlessNode(4, "l");
    ISimpleNode g = NodeFactory.createSingleChildNode(3, l, "g");
    ISimpleNode h = NodeFactory.createMultiChildNode(3, new ISimpleNode[]{j, k}, "h");
    ISimpleNode i = NodeFactory.createChildlessNode(3, "i");
    ISimpleNode d = NodeFactory.createSingleChildNode(2, g, "d");
    ISimpleNode e = NodeFactory.createMultiChildNode(2, new ISimpleNode[]{g, h, i}, "e");
    ISimpleNode f = NodeFactory.createSingleChildNode(2, h, "f");
    ISimpleNode a = NodeFactory.createSingleChildNode(1, e, "a");
    ISimpleNode b = NodeFactory.createSingleChildNode(1, d, "b");
    ISimpleNode c = NodeFactory.createSingleChildNode(1, d, "c");
    ISimpleNode[] simpleNodes = new ISimpleNode[]{a, b, c, d, e, f, g, h, i, j, k, l};
    IProperHierarchicalGraph graph = new ProperHierarchicalGraph(simpleNodes, 4);
    orderGraph(graph);
    assertTrue(Arrays.equals(new ISimpleNode[]{b, c, a}, graph.getNodesByLayer(1)));
    assertTrue(Arrays.equals(new ISimpleNode[]{d, e, f}, graph.getNodesByLayer(2)));
    assertTrue(Arrays.equals(new ISimpleNode[]{g, i, h}, graph.getNodesByLayer(3)));
    assertTrue(Arrays.equals(new ISimpleNode[]{l, k, j}, graph.getNodesByLayer(4)));
  }

  private void orderGraph(IProperHierarchicalGraph graph) {
    new UrsVertexOrderer(graph).processMultiLayerGraph();
  }

  /**
   * A vision for Solar Ride
   */
  @Test
  public void testSolarRide() throws Exception {
    ISimpleNode bridge = NodeFactory.createChildlessNode(4, "Bridge/Clouds");
    ISimpleNode windRace = NodeFactory.createChildlessNode(4, "Wind/Infusion");
    ISimpleNode flashing = NodeFactory.createChildlessNode(4, "Flashing Steed");
    ISimpleNode healing = NodeFactory.createChildlessNode(4, "Horse Healing");
    ISimpleNode spirit = NodeFactory.createChildlessNode(3, "Spirit Steed");
    ISimpleNode phantom = NodeFactory.createChildlessNode(3, "Phantom Steed");
    ISimpleNode partnership = NodeFactory.createSingleChildNode(3, windRace, "Flawless Partnership");
    ISimpleNode feathery = NodeFactory.createSingleChildNode(3, bridge, "Feathery Gallop");
    ISimpleNode sustaining = NodeFactory.createMultiChildNode(
      3,
      new ISimpleNode[]{windRace, flashing, healing},
      "Sustaining Method");
    ISimpleNode steadying = NodeFactory.createMultiChildNode(
      2,
      new ISimpleNode[]{partnership, feathery, sustaining},
      "Sustaining Method");
    ISimpleNode whistle = NodeFactory.createMultiChildNode(2, new ISimpleNode[]{spirit, phantom}, "Horse Whistle");
    ISimpleNode master = NodeFactory.createMultiChildNode(
      1,
      new ISimpleNode[]{steadying, whistle},
      "Master Horseman");
    ISimpleNode[] simpleNodes = new ISimpleNode[]{
      phantom,
      spirit,
      healing,
      flashing,
      windRace,
      bridge,
      master,
      whistle,
      steadying,
      sustaining,
      feathery,
      partnership,};
    IProperHierarchicalGraph graph = new ProperHierarchicalGraph(simpleNodes, 4);
    orderGraph(graph);
    assertEquals(0, graph.calculateNumberOfCrossings(1));
    assertEquals(0, graph.calculateNumberOfCrossings(2));
    assertEquals(0, graph.calculateNumberOfCrossings(3));
  }

  /**
   * An example taken from the Solar Craft cascade
   */
  @Test
  public void testPartialCraft() throws Exception {
    ISimpleNode G = NodeFactory.createChildlessNode(3, "G");
    ISimpleNode D = NodeFactory.createChildlessNode(2, "D");
    ISimpleNode C = NodeFactory.createChildlessNode(1, "C");
    ISimpleNode F = NodeFactory.createSingleChildNode(2, G, "F");
    ISimpleNode E = NodeFactory.createSingleChildNode(2, G, "E");
    ISimpleNode B = NodeFactory.createMultiChildNode(1, new ISimpleNode[]{D, E, F}, "B");
    ISimpleNode A = NodeFactory.createMultiChildNode(1, new ISimpleNode[]{E}, "A");
    ISimpleNode[] simpleNodes = new ISimpleNode[]{A, B, C, D, E, F, G};
    IProperHierarchicalGraph graph = new ProperHierarchicalGraph(simpleNodes, 3);
    orderGraph(graph);
    assertEquals(0, graph.calculateNumberOfCrossings(1));
    assertEquals(0, graph.calculateNumberOfCrossings(2));
  }
}