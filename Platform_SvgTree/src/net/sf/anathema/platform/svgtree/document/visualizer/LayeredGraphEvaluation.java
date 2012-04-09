package net.sf.anathema.platform.svgtree.document.visualizer;

import net.sf.anathema.graph.graph.LayeredGraph;
import net.sf.anathema.graph.nodes.ISimpleNode;

public class LayeredGraphEvaluation {

  private LayeredGraph graph;

  public LayeredGraphEvaluation(LayeredGraph graph) {
    this.graph = graph;
  }

  public boolean layerHasLeaved(int layer) {
    for (ISimpleNode node : graph.getNodesByLayer(layer)) {
      if (node.isLeafNode()) {
        return true;
      }
    }
    return false;
  }

  public int numberOfNodesForLayer(int layer) {
    return graph.getNodesByLayer(layer).length;
  }

  public boolean isPrecededByEquiformLayer(int layer) {
    ISimpleNode[] layerNodes = graph.getNodesByLayer(layer);
    ISimpleNode[] precedingLayerNodes = graph.getNodesByLayer(layer - 1);
    if (layerNodes.length != precedingLayerNodes.length) {
      return false;
    }
    for (ISimpleNode precedingLayerNode : precedingLayerNodes) {
      if (precedingLayerNode.getChildren().length != 1) {
        return false;
      }
    }
    return true;
  }

  public boolean isCompletelyConnectedToPrecedingLayer(int layer) {
    ISimpleNode[] layerNodes = graph.getNodesByLayer(layer);
    ISimpleNode[] precedingLayerNodes = graph.getNodesByLayer(layer - 1);
    for (ISimpleNode precedingLayerNode : precedingLayerNodes) {
      if (precedingLayerNode.getChildren().length != layerNodes.length) {
        return false;
      }
    }
    return true;
  }
}
