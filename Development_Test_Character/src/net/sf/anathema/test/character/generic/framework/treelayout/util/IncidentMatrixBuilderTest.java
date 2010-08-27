package net.sf.anathema.test.character.generic.framework.treelayout.util;

import net.sf.anathema.graph.graph.IProperHierarchicalGraph;
import net.sf.anathema.graph.graph.ProperHierarchicalGraph;
import net.sf.anathema.graph.nodes.ISimpleNode;
import net.sf.anathema.graph.nodes.NodeFactory;
import net.sf.anathema.graph.util.IncidentMatrixUtilities;
import net.sf.anathema.lib.testing.BasicTestCase;

public class IncidentMatrixBuilderTest extends BasicTestCase {

  public void testOneRootOneLeafOneConnection() throws Exception {
    ISimpleNode leaf = NodeFactory.createChildlessNode(2, "leaf"); //$NON-NLS-1$
    ISimpleNode root = NodeFactory.createSingleChildNode(1, leaf, "root"); //$NON-NLS-1$
    IProperHierarchicalGraph graph = new ProperHierarchicalGraph(new ISimpleNode[] { root, leaf }, 2);
    boolean[][] incidentMatrix = getIncidentMatrix(graph);
    assertEquals(true, incidentMatrix[0][0]);
  }

  private boolean[][] getIncidentMatrix(IProperHierarchicalGraph graph) {
    return IncidentMatrixUtilities.buildMatrix(graph.getNodesByLayer(1), graph.getNodesByLayer(2));
  }

  public void testTwoRootsOneLeafOneConnection() throws Exception {
    ISimpleNode leaf = NodeFactory.createChildlessNode(2, "leaf"); //$NON-NLS-1$
    ISimpleNode root1 = NodeFactory.createChildlessNode(1, "root1"); //$NON-NLS-1$
    ISimpleNode root2 = NodeFactory.createSingleChildNode(1, leaf, "root2"); //$NON-NLS-1$
    IProperHierarchicalGraph graph = new ProperHierarchicalGraph(new ISimpleNode[] { root1, root2, leaf }, 2);
    boolean[][] incidentMatrix = getIncidentMatrix(graph);
    assertEquals(false, incidentMatrix[0][0]);
    assertEquals(true, incidentMatrix[1][0]);
  }
}