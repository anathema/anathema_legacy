package net.sf.anathema.charmtree.provider;

import java.awt.Dimension;

import org.dom4j.Element;

public class VisualizedGraph implements IVisualizedGraph {

  private final Element cascadeElement;
  private final Dimension dimension;

  public VisualizedGraph(Element cascadeElement, Dimension dimension) {
    this.cascadeElement = cascadeElement;
    this.dimension = dimension;
  }

  public final Element getCascadeElement() {
    return cascadeElement;
  }

  public final Dimension getDimension() {
    return dimension;
  }

  public boolean isSingleNode() {
    return false;
  }
}