package net.sf.anathema.platform.svgtree.document.visualizer;

import java.awt.Dimension;


import net.sf.anathema.platform.svgtree.document.SvgDocumentBuilder;
import org.apache.batik.util.SVGConstants;
import org.dom4j.Element;

public class SvgVisualizedGraph implements IVisualizedGraph<SvgDocumentBuilder> {

  private final Element graphElement;
  private final Dimension dimension;
  private final boolean containsSingleNode;

  public SvgVisualizedGraph(Element graphElement, Dimension dimension, boolean containsSingleNode) {
    this.graphElement = graphElement;
    this.dimension = dimension;
    this.containsSingleNode = containsSingleNode;
  }

  @Override
  public Dimension getDimension() {
    return dimension;
  }

  @Override
  public boolean isSingleNode() {
    return containsSingleNode;
  }

  @Override
  public void translateBy(double x, double y) {
    graphElement.addAttribute(SVGConstants.SVG_TRANSFORM_ATTRIBUTE, "translate(" + x + " " + y + ")");
  }

  @Override
  public void addTo(SvgDocumentBuilder cascadeBuilder) {
    cascadeBuilder.add(graphElement);
  }
}