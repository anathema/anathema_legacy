package net.sf.anathema.platform.tree.view.visualizer;

import net.sf.anathema.graph.graph.IProperHierarchicalGraph;
import net.sf.anathema.platform.svgtree.document.VisualizerFactory;
import net.sf.anathema.platform.svgtree.document.visualizer.ICascadeVisualizer;
import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;
import net.sf.anathema.platform.svgtree.document.visualizer.NullCascadeVisualizer;

public class SwingVisualizerFactory implements VisualizerFactory {
  private final ITreePresentationProperties properties;

  public SwingVisualizerFactory(ITreePresentationProperties properties) {
    this.properties = properties;
  }

  @Override
  public ICascadeVisualizer createForBottomUp(IProperHierarchicalGraph graph) {
    return new NullCascadeVisualizer();
  }

  @Override
  public ICascadeVisualizer createForInvertedTree(IProperHierarchicalGraph graph) {
    return new NullCascadeVisualizer();
  }

  @Override
  public ICascadeVisualizer createForTree(IProperHierarchicalGraph graph) {
    return new NullCascadeVisualizer();
  }

  @Override
  public ICascadeVisualizer createForSingle(IProperHierarchicalGraph graph) {
    return new NullCascadeVisualizer();
  }
}