package net.sf.anathema.platform.svgtree.document.visualizer;

import net.sf.anathema.graph.graph.LayeredGraph;
import net.sf.anathema.graph.nodes.ISimpleNode;
import net.sf.anathema.platform.svgtree.document.components.ILayer;
import net.sf.anathema.platform.svgtree.document.components.IVisualizableNode;
import net.sf.anathema.platform.svgtree.document.components.Layer;

public class SingleNodePositioner extends AbstractCascadeVisualizer {

  public SingleNodePositioner(LayeredGraph graph, ITreePresentationProperties properties) {
    super(properties, graph);
  }

  @Override
  public ILayer[] buildTree() {
    ISimpleNode node = getGraph().getNodesByLayer(1)[0];
    IVisualizableNode visualizableNode = getNodeFactory().registerVisualizableNode(node);
    ILayer layer = new Layer(getProperties().getGapDimension(), 0);
    layer.addNode(visualizableNode);
    visualizableNode.setPosition(visualizableNode.getWidth() / 2);
    return new ILayer[]{layer};
  }
}