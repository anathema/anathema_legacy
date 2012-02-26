package net.sf.anathema.graph.hierarchy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.anathema.graph.nodes.DummyNode;
import net.sf.anathema.graph.nodes.IRegularNode;
import net.sf.anathema.graph.nodes.ISimpleNode;

public class HierarchyBuilder implements IHierachyBuilder {

  public ISimpleNode[] removeLongEdges(IRegularNode[] acyclicGraph) {
    List<ISimpleNode> hierarchicalGraph = new ArrayList<ISimpleNode>();
    Collections.addAll(hierarchicalGraph, acyclicGraph);
    for (IRegularNode node : acyclicGraph) {
      if (node.getLowerToChildren()) {
        int minimumChildLayer = Integer.MAX_VALUE;
        for (ISimpleNode child : node.getChildren()) {
          minimumChildLayer = Math.min(child.getLayer(), minimumChildLayer);
        }
        if (minimumChildLayer > node.getLayer()) {
          node.setLayer(minimumChildLayer - 1);
        }
      }
      for (ISimpleNode child : node.getChildren()) {
        if (isLongEdge(node, child)) {
          List<ISimpleNode> insertedNodes = insertDummyNodes(node, child);
          hierarchicalGraph.addAll(insertedNodes);
        }
      }
    }
    return hierarchicalGraph.toArray(new ISimpleNode[hierarchicalGraph.size()]);
  }

  private List<ISimpleNode> insertDummyNodes(IRegularNode node, ISimpleNode child) {
    List<ISimpleNode> dummyNodes = new ArrayList<ISimpleNode>();
    node.removeChild(child);
    child.removeParent(node);
    int nodeLayer = node.getLayer();
    ISimpleNode currentChild = child;
    while (currentChild.getLayer() - 1 > nodeLayer) {
      currentChild = createDummyEdge(currentChild);
      dummyNodes.add(currentChild);
    }
    node.addChild(currentChild);
    currentChild.addParent(node);
    return dummyNodes;
  }

  private DummyNode createDummyEdge(ISimpleNode child) {
    DummyNode dummyNode = new DummyNode(child, child.getLayer() - 1);
    child.addParent(dummyNode);
    return dummyNode;
  }

  private boolean isLongEdge(IRegularNode node, ISimpleNode child) {
    return getChildDistance(node, child) > 1;
  }

  private int getChildDistance(IRegularNode node, ISimpleNode child) {
    return child.getLayer() - node.getLayer();
  }
}