package net.sf.anathema.graph.layering;

import net.sf.anathema.graph.nodes.IRegularNode;
import net.sf.anathema.graph.nodes.ISimpleNode;

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
	// This is not currently useful, as the one "optimizer" in place produces often-questionable results,
	// and the new layer-overflow optimizer isn't yet supported by the SVG tree rendering code.
	
    // TODO: [2010-09-17] Improve the TwoCommonParentsOptimizer - or possibly optimization as a whole.
	/*
      MultiEntryMap<Integer, IRegularNode> nodesByLayer = new MultiEntryMap<Integer, IRegularNode>();
      for (IRegularNode node : topologicallySortedNodes) {
        nodesByLayer.add(node.getLayer(), node);
      }
      //for (int layerIndex = 1; layerIndex <= deepestLayer; layerIndex++) {
      //    deepestLayer = LayerOverflowOptimizer.splitLayer(nodesByLayer.get(layerIndex), deepestLayer);
      //}
      //for (int layerIndex = 2; layerIndex <= deepestLayer; layerIndex++) {
      //  deepestLayer = TwoCommonParentsOptimizer.moveDownOvercrossingTwoTupels(
      //      nodesByLayer.get(layerIndex),
      //      deepestLayer);
      //}
     */
    return deepestLayer;
  }

  private void setLayersToOne(IRegularNode[] topologicallySortedNodes) {
    for (IRegularNode node : topologicallySortedNodes) {
      node.setLayer(1);
    }
  }
}