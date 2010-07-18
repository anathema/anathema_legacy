package net.sf.anathema.graph.layering;

import net.sf.anathema.graph.nodes.IRegularNode;
import net.sf.anathema.graph.nodes.ISimpleNode;
import net.sf.anathema.lib.collection.MultiEntryMap;

public class LongestPathLayerer implements ILayerer {

  public int layerGraph(IRegularNode[] acyclicGraph) {
    IRegularNode[] topologicallySortedNodes = TopologyBuilder.sortGraphByTopology(acyclicGraph);
    setLayersToOne(topologicallySortedNodes);
    int deepestLayer = determineLayers(topologicallySortedNodes);
    deepestLayer = optimizeLayers(topologicallySortedNodes, deepestLayer);
    return deepestLayer;
  }

  private int determineLayers(IRegularNode[] topologicallySortedNodes) {
    int deepestLayer = 1;
    for (IRegularNode node : topologicallySortedNodes) {
      deepestLayer = setChildLayers(deepestLayer, node);
    }
    return deepestLayer;
  }

  private int setChildLayers(int deepestLayer, IRegularNode node) {
    for (ISimpleNode child : node.getChildren()) {
      IRegularNode regularChild = (IRegularNode) child;
      regularChild.setLayer(Math.max(regularChild.getLayer(), node.getLayer() + 1));
      deepestLayer = Math.max(regularChild.getLayer(), deepestLayer);
    }
    return deepestLayer;
  }

  private int optimizeLayers(IRegularNode[] topologicallySortedNodes, int deepestLayer) {
    MultiEntryMap<Integer, IRegularNode> nodesByLayer = new MultiEntryMap<Integer, IRegularNode>();
    for (IRegularNode node : topologicallySortedNodes) {
      nodesByLayer.add(node.getLayer(), node);
    }
    boolean increaseDeepestLayer = false;
    for (int layerIndex = 2; layerIndex <= deepestLayer; layerIndex++) {
      increaseDeepestLayer |= TwoCommonParentsOptimizer.moveDownOvercrossingTwoTupels(
          nodesByLayer.get(layerIndex),
          deepestLayer);
    }
    if (increaseDeepestLayer) {
      deepestLayer++;
    }
    return deepestLayer;
  }

  private void setLayersToOne(IRegularNode[] topologicallySortedNodes) {
    for (IRegularNode node : topologicallySortedNodes) {
      node.setLayer(1);
    }
  }
}