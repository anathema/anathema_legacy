package net.sf.anathema.platform.svgtree.document.visualizer;

import net.sf.anathema.platform.svgtree.document.ITreePresentationProperties;
import net.sf.anathema.platform.svgtree.document.components.ILayer;
import net.sf.anathema.platform.svgtree.document.components.nodes.IVisualizableNode;
import net.sf.anathema.platform.svgtree.graph.graph.IProperHierarchicalGraph;

public class TreeVisualizer extends AbstractTreeVisualizer {

  public TreeVisualizer(IProperHierarchicalGraph graph, ITreePresentationProperties properties) {
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