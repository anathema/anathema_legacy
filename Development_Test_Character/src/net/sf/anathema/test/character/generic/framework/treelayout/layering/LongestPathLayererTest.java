package net.sf.anathema.test.character.generic.framework.treelayout.layering;

import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.platform.svgtree.graph.layering.LongestPathLayerer;
import net.sf.anathema.platform.svgtree.graph.nodes.IRegularNode;
import net.sf.anathema.platform.svgtree.graph.nodes.IdentifiedRegularNode;
import net.sf.anathema.platform.svgtree.graph.nodes.NodeFactory;

public class LongestPathLayererTest extends BasicTestCase {

  private LongestPathLayerer layerer;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    layerer = new LongestPathLayerer();
  }

  public void testLayerSingleChild() throws Exception {
    IRegularNode leaf = NodeFactory.createChildlessNode("leaf"); //$NON-NLS-1$
    IRegularNode root = new IdentifiedRegularNode(new IRegularNode[] { leaf }, "root"); //$NON-NLS-1$
    IRegularNode[] acyclicGraph = new IRegularNode[] { leaf, root };
    int deepestLayer = layerer.layerGraph(acyclicGraph);
    assertEquals(2, deepestLayer);
    assertEquals(root.getLayer(), 1);
    assertEquals(leaf.getLayer(), 2);
  }

  public void testLayerDualChild() throws Exception {
    IRegularNode leaf1 = NodeFactory.createChildlessNode("leaf1"); //$NON-NLS-1$
    IRegularNode leaf2 = NodeFactory.createChildlessNode("leaf2"); //$NON-NLS-1$
    IRegularNode root = new IdentifiedRegularNode(new IRegularNode[] { leaf1, leaf2 }, "root"); //$NON-NLS-1$
    IRegularNode[] acyclicGraph = new IRegularNode[] { leaf1, leaf2, root };
    int deepestLayer = layerer.layerGraph(acyclicGraph);
    assertEquals(2, deepestLayer);
    assertEquals(root.getLayer(), 1);
    assertEquals(leaf1.getLayer(), 2);
    assertEquals(leaf2.getLayer(), 2);
  }

  public void testLayerDualRootDualChild() throws Exception {
    IRegularNode leaf1 = NodeFactory.createChildlessNode("leaf1"); //$NON-NLS-1$
    IRegularNode leaf2 = NodeFactory.createChildlessNode("leaf2"); //$NON-NLS-1$
    IRegularNode root1 = new IdentifiedRegularNode(new IRegularNode[] { leaf1, leaf2 }, "root1"); //$NON-NLS-1$
    IRegularNode root2 = new IdentifiedRegularNode(new IRegularNode[] { leaf1, leaf2 }, "root2"); //$NON-NLS-1$
    IRegularNode[] acyclicGraph = new IRegularNode[] { leaf1, leaf2, root1, root2 };
    int deepestLayer = layerer.layerGraph(acyclicGraph);
    assertEquals(3, deepestLayer);
    assertEquals(root1.getLayer(), 1);
    assertEquals(root2.getLayer(), 1);
    assertTrue(leaf1.getLayer() == 2 || leaf1.getLayer() == 3);
    assertTrue(leaf2.getLayer() == 2 || leaf2.getLayer() == 3);
    assertTrue(leaf1.getLayer() != leaf2.getLayer());
  }
}