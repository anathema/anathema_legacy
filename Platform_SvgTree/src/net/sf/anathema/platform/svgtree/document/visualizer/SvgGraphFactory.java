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
  public IVisualizedGraph create(ILayer[] layers) {
    boolean singleNode = layers.length == 1 && layers[0].getNodes().length == 1;
    return new SvgVisualizedGraph(createElement(layers), calculateSize(layers), singleNode);
  }

  private Dimension calculateSize(ILayer... layers) {
    return calculator.getTreeDimension(layers);
  }

  private Element createElement(ILayer... layers) {
    return new SvgLayerElementCreator(properties).createXml(layers);
  }
}