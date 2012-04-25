package net.sf.anathema.platform.svgtree.document.visualizer;

import java.awt.Dimension;


import org.dom4j.Element;

public class VisualizedGraph implements IVisualizedGraph {

  private final Element cascadeElement;
  private final Dimension dimension;

  public VisualizedGraph(Element cascadeElement, Dimension dimension) {
    this.cascadeElement = cascadeElement;
    this.dimension = dimension;
  }

  @Override
  public final Element getCascadeElement() {
    return cascadeElement;
  }

  @Override
  public final Dimension getDimension() {
    return dimension;
  }

  @Override
  public boolean isSingleNode() {
    return false;
  }
}