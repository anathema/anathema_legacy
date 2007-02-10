package net.sf.anathema.platform.svgtree.document.visualizer;

import net.sf.anathema.character.generic.framework.magic.treelayout.graph.IProperHierarchicalGraph;
import net.sf.anathema.character.generic.template.presentation.ITreePresentationProperties;
import net.sf.anathema.platform.svgtree.document.components.ILayer;
import net.sf.anathema.platform.svgtree.document.components.nodes.IVisualizableNode;

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