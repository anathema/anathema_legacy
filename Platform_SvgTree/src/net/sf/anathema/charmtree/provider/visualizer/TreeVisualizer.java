package net.sf.anathema.charmtree.provider.visualizer;

import net.sf.anathema.character.generic.framework.magic.treelayout.graph.IProperHierarchicalGraph;
import net.sf.anathema.character.generic.template.presentation.ITreePresentationProperties;
import net.sf.anathema.charmtree.provider.components.ILayer;
import net.sf.anathema.charmtree.provider.components.nodes.IVisualizableNode;

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