package net.sf.anathema.platform.svgtree.document.visualizer;

import org.dom4j.Element;

import java.awt.Dimension;

public class SingleNodeVisualizedGraph extends VisualizedGraph {

  public SingleNodeVisualizedGraph(Element graphElement, Dimension dimension) {
    super(graphElement, dimension);
  }

  @Override
  public boolean isSingleNode() {
    return true;
  }
}