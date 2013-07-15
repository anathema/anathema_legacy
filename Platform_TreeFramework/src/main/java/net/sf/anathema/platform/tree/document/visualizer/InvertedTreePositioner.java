package net.sf.anathema.platform.tree.document.visualizer;

import net.sf.anathema.graph.graph.LayeredGraph;
import net.sf.anathema.platform.tree.document.components.ILayer;
import net.sf.anathema.platform.tree.document.components.IVisualizableNode;

public class InvertedTreePositioner extends AbstractTreeVisualizer {

  public InvertedTreePositioner(LayeredGraph graph, TreePresentationProperties properties) {
    super(properties, graph);
  }

  @Override
  protected IVisualizableNode[] getRelatives(IVisualizableNode node) {
    return node.getParents();
  }

  @Override
  protected ILayer getInitialLayer(ILayer[] layers) {
    return layers[layers.length - 1];
  }
}
