package net.sf.anathema.platform.svgtree.document.visualizer;

import net.sf.anathema.graph.graph.LayeredGraph;
import net.sf.anathema.platform.svgtree.document.components.ILayer;
import net.sf.anathema.platform.svgtree.document.components.IVisualizableNode;

public class TreePositioner extends AbstractTreeVisualizer {

  public TreePositioner(LayeredGraph graph, ITreePresentationProperties properties) {
    super(properties, graph);
  }

  @Override
  protected IVisualizableNode[] getRelatives(IVisualizableNode node) {
    return node.getChildren();
  }

  @Override
  protected ILayer getInitialLayer(ILayer[] layers) {
    return layers[0];
  }
}