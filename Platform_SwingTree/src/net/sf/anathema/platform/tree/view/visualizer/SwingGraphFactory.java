package net.sf.anathema.platform.tree.view.visualizer;

import net.sf.anathema.platform.svgtree.document.components.ILayer;
import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;
import net.sf.anathema.platform.svgtree.document.visualizer.IVisualizedGraph;
import net.sf.anathema.platform.svgtree.document.visualizer.NullVisualizedGraph;
import net.sf.anathema.platform.svgtree.document.visualizer.VisualizedGraphFactory;

import java.awt.Dimension;

public class SwingGraphFactory implements VisualizedGraphFactory {
  public SwingGraphFactory(ITreePresentationProperties properties) {
  }

  @Override
  public IVisualizedGraph createForSingleNode(ILayer layer) {
    return new NullVisualizedGraph();
  }

  @Override
  public IVisualizedGraph create(ILayer[] layers) {
    return new NullVisualizedGraph();
  }

  @Override
  public IVisualizedGraph createWithDimension(ILayer[] layers, Dimension dimension) {
    return new NullVisualizedGraph();
  }
}