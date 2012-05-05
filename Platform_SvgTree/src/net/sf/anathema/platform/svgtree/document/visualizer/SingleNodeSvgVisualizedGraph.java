package net.sf.anathema.platform.svgtree.document.visualizer;

import org.dom4j.Element;

import java.awt.Dimension;

public class SingleNodeSvgVisualizedGraph extends SvgVisualizedGraph {

  public SingleNodeSvgVisualizedGraph(Element graphElement, Dimension dimension) {
    super(graphElement, dimension);
  }

  @Override
  public boolean isSingleNode() {
    return true;
  }
}