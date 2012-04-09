package net.sf.anathema.platform.svgtree.document.visualizer;

import net.sf.anathema.graph.graph.LayeredGraph;
import net.sf.anathema.graph.nodes.ISimpleNode;
import net.sf.anathema.lib.collection.ListOrderedSet;
import net.sf.anathema.platform.svgtree.document.components.ILayer;
import net.sf.anathema.platform.svgtree.document.util.BackwardsIterable;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Arrays;

public class SimplifiedButtonUpGraphVisualizer extends AbstractCascadeVisualizer {

  public SimplifiedButtonUpGraphVisualizer(LayeredGraph graph, final ITreePresentationProperties properties) {
    super(new WideDummyTreePresentationProperties(properties), graph);
  }

  @Override
  public IVisualizedGraph buildTree() {
    ILayer[] layers = createLayers();
    int width = 0;
    for (ILayer layer : layers) {
      width = Math.max(width, layer.getNodes()[0].getWidth());
    }
    for (ILayer layer : new BackwardsIterable<ILayer>(layers)) {
      layer.getNodes()[0].setPosition(width / 2);
    }
    for (ILayer layer : new BackwardsIterable<ILayer>(layers)) {
      layer.unrollHorizontalMetanodes();
    }
    return new VisualizedGraph(createXml(layers), getTreeDimension(layers));
  }

  public boolean isApplicable() {
    LayeredGraphEvaluation evaluation = new LayeredGraphEvaluation(getGraph());
    int deepestLayer = getGraph().getDeepestLayer();
    for (int index = 1; index < deepestLayer; index++) {
      if (evaluation.layerHasLeaved(index)) {
        return false;
      }
    }
    if (evaluation.numberOfNodesForLayer(deepestLayer) > 1) {
      if (!evaluation.isPrecededByEquiformLayer(deepestLayer) && !evaluation.isCompletelyConnectedToPrecedingLayer(
              deepestLayer)) {
        return false;
      }
    }
    return true;
  }

  private ILayer[] createLayers() {
    int layerCount = getGraph().getDeepestLayer();
    for (int layerIndex = layerCount - 1; layerIndex >= 0; layerIndex--) {
      createVisualizableNodes(layerIndex);
    }
    return createLayers(layerCount);
  }

  private void createVisualizableNodes(int layerIndex) {
    ISimpleNode[] layerNodes = getGraph().getNodesByLayer(layerIndex + 1);
    if (layerNodes.length > 1) {
      ListOrderedSet<ISimpleNode> nodeSet = new ListOrderedSet<ISimpleNode>();
      nodeSet.addAll(Arrays.asList(layerNodes));
      getNodeFactory().registerHorizontalMetaNode(nodeSet);
    } else {
      ISimpleNode singleNode = layerNodes[0];
      if (!isVisualizableNodeRegistered(singleNode)) {
        getNodeFactory().registerVisualizableNode(singleNode);
      }
    }
  }

  private static class WideDummyTreePresentationProperties implements ITreePresentationProperties {
    private final ITreePresentationProperties properties;

    public WideDummyTreePresentationProperties(ITreePresentationProperties properties) {
      this.properties = properties;
    }

    @Override
    public String getNodeFramePolygonString() {
      return properties.getNodeFramePolygonString();
    }

    @Override
    public Dimension getNodeDimension() {
      return properties.getNodeDimension();
    }

    @Override
    public Dimension getGapDimension() {
      return properties.getGapDimension();
    }

    @Override
    public Dimension getVerticalLineDimension() {
      return properties.getNodeDimension();
    }

    @Override
    public Color getColor() {
      return properties.getColor();
    }
  }
}
