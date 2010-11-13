package net.sf.anathema.graph.layering;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.graph.nodes.IRegularNode;
import net.sf.anathema.graph.nodes.ISimpleNode;

public class TopologyBuilder {

  public static IRegularNode[] sortGraphByTopology(IRegularNode[] acyclicGraph) {
    List<ISimpleNode> topologicalSort = new ArrayList<ISimpleNode>();
    for (IRegularNode node : acyclicGraph) {
      if (node.isRootNode()) {
        sortIntoSetRecursively(node, topologicalSort);
      }
    }
    return topologicalSort.toArray(new IRegularNode[topologicalSort.size()]);
  }

  private static void sortIntoSetRecursively(ISimpleNode node, List<ISimpleNode> topologicalSort) {
    for (ISimpleNode child : node.getChildren()) {
      sortIntoSetRecursively(child, topologicalSort);
    }
    if (!topologicalSort.contains(node)) {
      topologicalSort.add(0, node);
    }
  }
}