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
  private final ITreePresentationProperties properties;

  public SwingGraphFactory(ITreePresentationProperties properties) {
    this.properties = properties;
    this.calculator = new TreeDimensionCalculator(properties);
  }

  @Override
  public IVisualizedGraph create(ILayer... layers) {
    boolean singleNode = layers.length == 1 && layers[0].getNodes().length == 1;
    return new SwingGraph(createCascade(layers), calculateSize(layers), singleNode);
  }

  private Dimension calculateSize(ILayer... layers) {
    return calculator.getTreeDimension(layers);
  }

  private DefaultContainerCascade createCascade(ILayer... layers) {
    return new SwingLayerCascadeCreator(properties).create(layers);
  }
}