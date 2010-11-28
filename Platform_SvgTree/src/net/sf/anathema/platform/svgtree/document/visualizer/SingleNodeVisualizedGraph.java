package net.sf.anathema.platform.svgtree.document.visualizer;

import java.awt.Dimension;


import org.dom4j.Element;

public class SingleNodeVisualizedGraph extends VisualizedGraph {

  public SingleNodeVisualizedGraph(Element cascadeElement, Dimension dimension) {
    super(cascadeElement, dimension);
  }

  @Override
  public boolean isSingleNode() {
    return true;
  }
}