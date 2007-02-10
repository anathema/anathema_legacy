package net.sf.anathema.platform.svgtree.document.visualizer;

import net.sf.anathema.character.generic.template.presentation.ITreePresentationProperties;
import net.sf.anathema.platform.svgtree.document.components.ILayer;
import net.sf.anathema.platform.svgtree.document.components.nodes.IVisualizableNode;
import net.sf.anathema.platform.svgtree.graph.graph.IProperHierarchicalGraph;

public class InvertedTreeVisualizer extends AbstractTreeVisualizer {

  public InvertedTreeVisualizer(IProperHierarchicalGraph graph, ITreePresentationProperties properties) {
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