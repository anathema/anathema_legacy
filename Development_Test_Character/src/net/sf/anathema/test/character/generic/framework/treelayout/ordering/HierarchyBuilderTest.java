package net.sf.anathema.test.character.generic.framework.treelayout.ordering;

import junit.framework.TestCase;
import net.sf.anathema.graph.hierarchy.HierarchyBuilder;
import net.sf.anathema.graph.hierarchy.IHierachyBuilder;
import net.sf.anathema.graph.nodes.IRegularNode;
import net.sf.anathema.graph.nodes.ISimpleNode;
import net.sf.anathema.graph.nodes.NodeFactory;

public class HierarchyBuilderTest extends TestCase {

  public void testSimpleDummyEdge() throws Exception {
    IRegularNode leaf = NodeFactory.createChildlessNode(3, "leaf"); //$NON-NLS-1$
    IRegularNode root = NodeFactory.createSingleChildNode(1, leaf, "root"); //$NON-NLS-1$
    IHierachyBuilder builder = new HierarchyBuilder();
    ISimpleNode[] graph = builder.removeLongEdges(new IRegularNode[] { leaf, root });
    assertEquals(3, graph.length);
    assertNotSame(leaf, root.getChildren()[0]);
    assertEquals(leaf, root.getChildren()[0].getChildren()[0]);
  }
}