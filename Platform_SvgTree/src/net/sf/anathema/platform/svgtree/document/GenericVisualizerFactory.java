package net.sf.anathema.platform.svgtree.document;

import net.sf.anathema.graph.graph.IProperHierarchicalGraph;
import net.sf.anathema.platform.svgtree.document.visualizer.BottomUpGraphVisualizer;
import net.sf.anathema.platform.svgtree.document.visualizer.ICascadeVisualizer;
import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;
import net.sf.anathema.platform.svgtree.document.visualizer.InvertedTreeVisualizer;
import net.sf.anathema.platform.svgtree.document.visualizer.SingleNodeVisualizer;
import net.sf.anathema.platform.svgtree.document.visualizer.TreeVisualizer;
import net.sf.anathema.platform.svgtree.document.visualizer.VisualizedGraphFactory;

public class GenericVisualizerFactory implements VisualizerFactory {
  private final ITreePresentationProperties properties;
  private final VisualizedGraphFactory factory;

  public GenericVisualizerFactory(ITreePresentationProperties properties, VisualizedGraphFactory graphFactory) {
    this.properties = properties;
    this.factory = graphFactory;
  }

  @Override
  public ICascadeVisualizer createForBottomUp(IProperHierarchicalGraph graph) {
    return new BottomUpGraphVisualizer(graph, properties, factory);
  }

  @Override
  public ICascadeVisualizer createForInvertedTree(IProperHierarchicalGraph graph) {
    return new InvertedTreeVisualizer(graph, properties, factory);
  }

  @Override
  public ICascadeVisualizer createForTree(IProperHierarchicalGraph graph) {
    return new TreeVisualizer(graph, properties, factory);
  }

  @Override
  public ICascadeVisualizer createForSingle(IProperHierarchicalGraph graph) {
    return new SingleNodeVisualizer(graph, properties, factory);
  }
}