package net.sf.anathema.platform.tree.view;

import net.sf.anathema.graph.graph.IProperHierarchicalGraph;
import net.sf.anathema.platform.svgtree.document.VisualizerFactory;
import net.sf.anathema.platform.svgtree.document.visualizer.ICascadeVisualizer;
import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;

public class SwingVisualizerFactory implements VisualizerFactory {
  private final ITreePresentationProperties properties;

  public SwingVisualizerFactory(ITreePresentationProperties properties) {
    this.properties = properties;
  }

  @Override
  public ICascadeVisualizer createForBottomUp(IProperHierarchicalGraph graph) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public ICascadeVisualizer createForInvertedTree(IProperHierarchicalGraph graph) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public ICascadeVisualizer createForTree(IProperHierarchicalGraph graph) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public ICascadeVisualizer createForSingle(IProperHierarchicalGraph graph) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }
}