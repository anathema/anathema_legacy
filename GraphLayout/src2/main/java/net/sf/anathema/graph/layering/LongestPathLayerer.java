package net.sf.anathema.graph.layering;

import net.sf.anathema.graph.nodes.IRegularNode;
import net.sf.anathema.graph.nodes.ISimpleNode;

public class LongestPathLayerer implements ILayerer {

  @Override
  public int layerGraph(IRegularNode[] acyclicGraph) {
    IRegularNode[] topologicallySortedNodes = TopologyBuilder.sortGraphByTopology(acyclicGraph);
    setLayersToOne(topologicallySortedNodes);
    return determineLayers(topologicallySortedNodes);
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

  private void setLayersToOne(IRegularNode[] topologicallySortedNodes) {
    for (IRegularNode node : topologicallySortedNodes) {
      node.setLayer(1);
    }
  }
}