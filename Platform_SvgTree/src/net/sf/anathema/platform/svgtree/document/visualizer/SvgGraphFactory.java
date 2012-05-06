package net.sf.anathema.platform.svgtree.document.visualizer;

import net.sf.anathema.platform.svgtree.document.components.ILayer;
import org.dom4j.Element;

import java.awt.Dimension;

public class SvgGraphFactory implements VisualizedGraphFactory {
  private final ITreePresentationProperties properties;
  private final TreeDimensionCalculator calculator;

  public SvgGraphFactory(ITreePresentationProperties properties) {
    this.properties = properties;
    this.calculator = new TreeDimensionCalculator(properties);
  }

  @Override
  public IVisualizedGraph createForSingleNode(ILayer layer) {
    return new SvgVisualizedGraph(createElement(layer), calculateSize(layer), true);
  }

  @Override
  public IVisualizedGraph create(ILayer[] layers) {
    return createWithDimension(layers, calculateSize(layers));
  }

  @Override
  public IVisualizedGraph createWithDimension(ILayer[] layers, Dimension dimension) {
    return new SvgVisualizedGraph(createElement(layers), dimension, false);
  }

  private Dimension calculateSize(ILayer... layers) {
    return calculator.getTreeDimension(layers);
  }

  private Element createElement(ILayer... layers) {
    return new SvgLayerElementCreator(properties).createXml(layers);
  }
}