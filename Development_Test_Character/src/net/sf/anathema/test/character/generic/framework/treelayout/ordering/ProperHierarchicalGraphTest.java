package net.sf.anathema.test.character.generic.framework.treelayout.ordering;

import java.util.Arrays;

import net.disy.commons.core.util.ContractFailedException;
import net.sf.anathema.graph.graph.IProperHierarchicalGraph;
import net.sf.anathema.graph.graph.ProperHierarchicalGraph;
import net.sf.anathema.graph.nodes.IRegularNode;
import net.sf.anathema.graph.nodes.ISimpleNode;
import net.sf.anathema.graph.nodes.NodeFactory;
import net.sf.anathema.lib.testing.BasicTestCase;

public class ProperHierarchicalGraphTest extends BasicTestCase {

  public void testSingleNodeGraph() throws Exception {
    IRegularNode node = NodeFactory.createChildlessNode(1, "node"); //$NON-NLS-1$
    IProperHierarchicalGraph graph = new ProperHierarchicalGraph(new ISimpleNode[] { node }, 1);
    assertNotNull(graph);
  }

  public void testDualLayerGraph() throws Exception {
    ISimpleNode node = NodeFactory.createChildlessNode(2, "node"); //$NON-NLS-1$
    ISimpleNode root = NodeFactory.createSingleChildNode(1, node, "root"); //$NON-NLS-1$
    IProperHierarchicalGraph graph = new ProperHierarchicalGraph(new ISimpleNode[] { root, node }, 2);
    assertEquals(root, graph.getNodesByLayer(1)[0]);
    assertEquals(node, graph.getNodesByLayer(2)[0]);
  }

  public void testLongEdgeException() throws Exception {
    try {
      ISimpleNode node = NodeFactory.createChildlessNode(3, "node"); //$NON-NLS-1$
      ISimpleNode root = NodeFactory.createSingleChildNode(1, node, "root"); //$NON-NLS-1$
      new ProperHierarchicalGraph(new ISimpleNode[] { root, node }, 3);
      fail();
    }
    catch (ContractFailedException e) {
      // nothing to do
    }
  }

  public void testLayerOrderUnchanged() throws Exception {
    ISimpleNode node1 = NodeFactory.createChildlessNode(1, "node1"); //$NON-NLS-1$
    ISimpleNode node2 = NodeFactory.createChildlessNode(1, "node2"); //$NON-NLS-1$
    ISimpleNode node3 = NodeFactory.createChildlessNode(1, "node3"); //$NON-NLS-1$
    ISimpleNode[] originalNodeArray = new ISimpleNode[] { node1, node2, node3 };
    IProperHierarchicalGraph graph = new ProperHierarchicalGraph(originalNodeArray, 1);
    ISimpleNode[] layerOneNodes = graph.getNodesByLayer(1);
    assertTrue(Arrays.equals(originalNodeArray, layerOneNodes));
  }

  public void testSetNewLayerOrder() throws Exception {
    ISimpleNode node1 = NodeFactory.createChildlessNode(1, "node1"); //$NON-NLS-1$
    ISimpleNode node2 = NodeFactory.createChildlessNode(1, "node2"); //$NON-NLS-1$
    ISimpleNode node3 = NodeFactory.createChildlessNode(1, "node3"); //$NON-NLS-1$
    IProperHierarchicalGraph graph = new ProperHierarchicalGraph(new ISimpleNode[] { node1, node2, node3 }, 1);
    ISimpleNode[] reorderedNodes = new ISimpleNode[] { node2, node3, node1 };
    graph.setNewLayerOrder(1, reorderedNodes);
    assertTrue(Arrays.equals(reorderedNodes, graph.getNodesByLayer(1)));
  }

  public void testNodeExchangedException() throws Exception {
    ISimpleNode node1 = NodeFactory.createChildlessNode(1, "node1"); //$NON-NLS-1$
    ISimpleNode node2 = NodeFactory.createChildlessNode(1, "node2"); //$NON-NLS-1$
    ISimpleNode node3 = NodeFactory.createChildlessNode(1, "node3"); //$NON-NLS-1$
    ISimpleNode secondLayerNode = NodeFactory.createChildlessNode(2, "secondLayer"); //$NON-NLS-1$
    IProperHierarchicalGraph graph = new ProperHierarchicalGraph(new ISimpleNode[] {
        node1,
        node2,
        node3,
        secondLayerNode }, 2);
    try {
      ISimpleNode[] reorderedNodes = new ISimpleNode[] { node2, node3, secondLayerNode };
      graph.setNewLayerOrder(1, reorderedNodes);
      fail();
    }
    catch (Exception e) {
      assertTrue(Arrays.equals(new ISimpleNode[] { node1, node2, node3 }, graph.getNodesByLayer(1)));
    }
  }

  public void testNodeRemovedException() throws Exception {
    ISimpleNode node1 = NodeFactory.createChildlessNode(1, "node1"); //$NON-NLS-1$
    ISimpleNode node2 = NodeFactory.createChildlessNode(1, "node2"); //$NON-NLS-1$
    ISimpleNode node3 = NodeFactory.createChildlessNode(1, "node3"); //$NON-NLS-1$
    IProperHierarchicalGraph graph = new ProperHierarchicalGraph(new ISimpleNode[] { node1, node2, node3 }, 1);
    try {
      ISimpleNode[] reorderedNodes = new ISimpleNode[] { node2, node3 };
      graph.setNewLayerOrder(1, reorderedNodes);
      fail();
    }
    catch (Exception e) {
      assertTrue(Arrays.equals(new ISimpleNode[] { node1, node2, node3 }, graph.getNodesByLayer(1)));
    }
  }
}