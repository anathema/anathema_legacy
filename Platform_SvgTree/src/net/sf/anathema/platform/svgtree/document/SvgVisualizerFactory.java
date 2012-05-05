package net.sf.anathema.platform.svgtree.document;

import net.sf.anathema.graph.graph.IProperHierarchicalGraph;
import net.sf.anathema.platform.svgtree.document.visualizer.BottomUpGraphVisualizer;
import net.sf.anathema.platform.svgtree.document.visualizer.ICascadeVisualizer;
import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;
import net.sf.anathema.platform.svgtree.document.visualizer.InvertedTreeVisualizer;
import net.sf.anathema.platform.svgtree.document.visualizer.SingleNodeVisualizer;
import net.sf.anathema.platform.svgtree.document.visualizer.TreeVisualizer;

public class SvgVisualizerFactory implements VisualizerFactory {
  private final ITreePresentationProperties properties;

  public SvgVisualizerFactory(ITreePresentationProperties properties) {
    this.properties = properties;
  }

  @Override
  public ICascadeVisualizer createForBottomUp(IProperHierarchicalGraph graph) {
    return new BottomUpGraphVisualizer(graph, properties);
  }

  @Override
  public ICascadeVisualizer createForInvertedTree(IProperHierarchicalGraph graph) {
    return new InvertedTreeVisualizer(graph, properties);
  }

  @Override
  public ICascadeVisualizer createForTree(IProperHierarchicalGraph graph) {
    return new TreeVisualizer(graph, properties);
  }

  @Override
  public ICascadeVisualizer createForSingle(IProperHierarchicalGraph graph) {
    return new SingleNodeVisualizer(properties, graph);
  }
}