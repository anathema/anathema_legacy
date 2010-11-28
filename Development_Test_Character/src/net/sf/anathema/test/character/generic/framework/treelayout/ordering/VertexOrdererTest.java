package net.sf.anathema.test.character.generic.framework.treelayout.ordering;

import java.util.Arrays;

import net.sf.anathema.graph.graph.IProperHierarchicalGraph;
import net.sf.anathema.graph.graph.ProperHierarchicalGraph;
import net.sf.anathema.graph.nodes.ISimpleNode;
import net.sf.anathema.graph.nodes.NodeFactory;
import net.sf.anathema.graph.ordering.UrsVertexOrderer;
import net.sf.anathema.lib.testing.BasicTestCase;

public class VertexOrdererTest extends BasicTestCase {

  public void testNoCrossingsUnchanged() throws Exception {
    ISimpleNode leaf1 = NodeFactory.createChildlessNode(2, "leaf1"); //$NON-NLS-1$
    ISimpleNode root1 = NodeFactory.createSingleChildNode(1, leaf1, "root1"); //$NON-NLS-1$
    ISimpleNode leaf2 = NodeFactory.createChildlessNode(2, "leaf2"); //$NON-NLS-1$
    ISimpleNode root2 = NodeFactory.createSingleChildNode(1, leaf2, "root2"); //$NON-NLS-1$
    IProperHierarchicalGraph graph = new ProperHierarchicalGraph(new ISimpleNode[] { root1, root2, leaf1, leaf2 }, 2);
    orderGraph(graph);
    assertTrue(Arrays.equals(new ISimpleNode[] { root1, root2 }, graph.getNodesByLayer(1)));
    assertTrue(Arrays.equals(new ISimpleNode[] { leaf1, leaf2 }, graph.getNodesByLayer(2)));
  }

  public void testOneCrossingSolved() throws Exception {
    ISimpleNode leaf1 = NodeFactory.createChildlessNode(2, "leaf1"); //$NON-NLS-1$
    ISimpleNode root1 = NodeFactory.createSingleChildNode(1, leaf1, "root1"); //$NON-NLS-1$
    ISimpleNode leaf2 = NodeFactory.createChildlessNode(2, "leaf2"); //$NON-NLS-1$
    ISimpleNode root2 = NodeFactory.createSingleChildNode(1, leaf2, "root2"); //$NON-NLS-1$
    ISimpleNode[] simpleNodes = new ISimpleNode[] { root2, root1, leaf1, leaf2 };
    IProperHierarchicalGraph graph = new ProperHierarchicalGraph(simpleNodes, 2);
    orderGraph(graph);
    assertTrue(Arrays.equals(new ISimpleNode[] { root2, root1 }, graph.getNodesByLayer(1)));
    assertTrue(Arrays.equals(new ISimpleNode[] { leaf2, leaf1 }, graph.getNodesByLayer(2)));
  }

  /** Setup and solution from Sugiyama, page 74 */
  public void testSugiyamaFourLayerExample() throws Exception {
    ISimpleNode j = NodeFactory.createChildlessNode(4, "j"); //$NON-NLS-1$
    ISimpleNode k = NodeFactory.createChildlessNode(4, "k"); //$NON-NLS-1$
    ISimpleNode l = NodeFactory.createChildlessNode(4, "l"); //$NON-NLS-1$
    ISimpleNode g = NodeFactory.createSingleChildNode(3, l, "g"); //$NON-NLS-1$
    ISimpleNode h = NodeFactory.createMultiChildNode(3, new ISimpleNode[] { j, k }, "h"); //$NON-NLS-1$
    ISimpleNode i = NodeFactory.createChildlessNode(3, "i"); //$NON-NLS-1$
    ISimpleNode d = NodeFactory.createSingleChildNode(2, g, "d"); //$NON-NLS-1$
    ISimpleNode e = NodeFactory.createMultiChildNode(2, new ISimpleNode[] { g, h, i }, "e"); //$NON-NLS-1$
    ISimpleNode f = NodeFactory.createSingleChildNode(2, h, "f"); //$NON-NLS-1$
    ISimpleNode a = NodeFactory.createSingleChildNode(1, e, "a"); //$NON-NLS-1$
    ISimpleNode b = NodeFactory.createSingleChildNode(1, d, "b"); //$NON-NLS-1$
    ISimpleNode c = NodeFactory.createSingleChildNode(1, d, "c"); //$NON-NLS-1$
    ISimpleNode[] simpleNodes = new ISimpleNode[] { a, b, c, d, e, f, g, h, i, j, k, l };
    IProperHierarchicalGraph graph = new ProperHierarchicalGraph(simpleNodes, 4);
    orderGraph(graph);
    assertTrue(Arrays.equals(new ISimpleNode[] { b, c, a }, graph.getNodesByLayer(1)));
    assertTrue(Arrays.equals(new ISimpleNode[] { d, e, f }, graph.getNodesByLayer(2)));
    assertTrue(Arrays.equals(new ISimpleNode[] { g, i, h }, graph.getNodesByLayer(3)));
    assertTrue(Arrays.equals(new ISimpleNode[] { l, k, j }, graph.getNodesByLayer(4)));
  }

  private void orderGraph(IProperHierarchicalGraph graph) {
    new UrsVertexOrderer(graph).processMultiLayerGraph();
  }

  /** A vision for Solar Ride */
  public void testSolarRide() throws Exception {
    ISimpleNode bridge = NodeFactory.createChildlessNode(4, "Bridge/Clouds"); //$NON-NLS-1$
    ISimpleNode windRace = NodeFactory.createChildlessNode(4, "Wind/Infusion"); //$NON-NLS-1$
    ISimpleNode flashing = NodeFactory.createChildlessNode(4, "Flashing Steed"); //$NON-NLS-1$
    ISimpleNode healing = NodeFactory.createChildlessNode(4, "Horse Healing"); //$NON-NLS-1$
    ISimpleNode spirit = NodeFactory.createChildlessNode(3, "Spirit Steed"); //$NON-NLS-1$
    ISimpleNode phantom = NodeFactory.createChildlessNode(3, "Phantom Steed"); //$NON-NLS-1$
    ISimpleNode partnership = NodeFactory.createSingleChildNode(3, windRace, "Flawless Partnership"); //$NON-NLS-1$
    ISimpleNode feathery = NodeFactory.createSingleChildNode(3, bridge, "Feathery Gallop"); //$NON-NLS-1$
    ISimpleNode sustaining = NodeFactory.createMultiChildNode(
        3,
        new ISimpleNode[] { windRace, flashing, healing },
        "Sustaining Method"); //$NON-NLS-1$
    ISimpleNode steadying = NodeFactory.createMultiChildNode(
        2,
        new ISimpleNode[] { partnership, feathery, sustaining },
        "Sustaining Method"); //$NON-NLS-1$
    ISimpleNode whistle = NodeFactory.createMultiChildNode(2, new ISimpleNode[] { spirit, phantom }, "Horse Whistle"); //$NON-NLS-1$
    ISimpleNode master = NodeFactory.createMultiChildNode(
        1,
        new ISimpleNode[] { steadying, whistle },
        "Master Horseman"); //$NON-NLS-1$
    ISimpleNode[] simpleNodes = new ISimpleNode[] {
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
        partnership, };
    IProperHierarchicalGraph graph = new ProperHierarchicalGraph(simpleNodes, 4);
    orderGraph(graph);
    assertEquals(0, graph.calculateNumberOfCrossings(1));
    assertEquals(0, graph.calculateNumberOfCrossings(2));
    assertEquals(0, graph.calculateNumberOfCrossings(3));
  }

  /** An example taken from the Solar Craft cascade */
  public void testPartialCraft() throws Exception {
    ISimpleNode G = NodeFactory.createChildlessNode(3, "G"); //$NON-NLS-1$
    ISimpleNode D = NodeFactory.createChildlessNode(2, "D"); //$NON-NLS-1$
    ISimpleNode C = NodeFactory.createChildlessNode(1, "C"); //$NON-NLS-1$
    ISimpleNode F = NodeFactory.createSingleChildNode(2, G, "F"); //$NON-NLS-1$
    ISimpleNode E = NodeFactory.createSingleChildNode(2, G, "E"); //$NON-NLS-1$
    ISimpleNode B = NodeFactory.createMultiChildNode(1, new ISimpleNode[] { D, E, F }, "B"); //$NON-NLS-1$
    ISimpleNode A = NodeFactory.createMultiChildNode(1, new ISimpleNode[] { E }, "A"); //$NON-NLS-1$      
    ISimpleNode[] simpleNodes = new ISimpleNode[] { A, B, C, D, E, F, G };
    IProperHierarchicalGraph graph = new ProperHierarchicalGraph(simpleNodes, 3);
    orderGraph(graph);
    assertEquals(0, graph.calculateNumberOfCrossings(1));
    assertEquals(0, graph.calculateNumberOfCrossings(2));
  }
}