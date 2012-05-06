package net.sf.anathema.platform.tree.view.visualizer;

import net.sf.anathema.platform.svgtree.document.components.ILayer;
import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;
import net.sf.anathema.platform.svgtree.document.visualizer.IVisualizedGraph;
import net.sf.anathema.platform.svgtree.document.visualizer.TreeDimensionCalculator;
import net.sf.anathema.platform.svgtree.document.visualizer.VisualizedGraphFactory;
import net.sf.anathema.platform.tree.view.container.DefaultContainerCascade;

import java.awt.Dimension;

public class SwingGraphFactory implements VisualizedGraphFactory {
  private TreeDimensionCalculator calculator;

  public SwingGraphFactory(ITreePresentationProperties properties) {
    this.calculator = new TreeDimensionCalculator(properties);
  }

  @Override
  public IVisualizedGraph createForSingleNode(ILayer layer) {
    return new SwingGraph(createCascade(layer), calculateSize(layer), true);
  }

  @Override
  public IVisualizedGraph create(ILayer... layers) {
    return createWithDimension(layers, calculateSize(layers));
  }

  @Override
  public IVisualizedGraph createWithDimension(ILayer[] layers, Dimension dimension) {
    return new SwingGraph(createCascade(layers), dimension, false);
  }

  private Dimension calculateSize(ILayer... layers) {
    return calculator.getTreeDimension(layers);
  }

  private DefaultContainerCascade createCascade(ILayer... layers) {
    return new DefaultContainerCascade();
  }
}