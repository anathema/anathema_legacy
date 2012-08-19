package net.sf.anathema.platform.tree.document.visualizer;

import net.sf.anathema.platform.tree.document.components.ILayer;

import java.awt.Dimension;

public class TreeDimensionCalculator {

  private final ITreePresentationProperties properties;

  public TreeDimensionCalculator(ITreePresentationProperties properties) {
    this.properties = properties;
  }

  public Dimension getTreeDimension(ILayer... layers) {
    return new Dimension(getTreeWidth(layers), getTreeHeight(layers));
  }

  public int getTreeWidth(ILayer... layers) {
    int width = 0;
    for (ILayer layer : layers) {
      width = Math.max(width, layer.getWidth());
    }
    return width;
  }

  public int getTreeHeight(ILayer... layers) {
    return layers.length * properties.getNodeDimension().height + (layers.length - 1) * properties.getGapDimension().height;
  }
}