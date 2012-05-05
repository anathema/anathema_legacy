package net.sf.anathema.platform.svgtree.document.visualizer;

import net.sf.anathema.platform.svgtree.document.components.HorizontalMetaNode;
import net.sf.anathema.platform.svgtree.document.components.ILayer;
import net.sf.anathema.platform.svgtree.document.components.IVisualizableNodeVisitor;
import net.sf.anathema.platform.svgtree.document.components.SvgNodeElementAdder;
import net.sf.anathema.platform.svgtree.document.components.VisualizableDummyNode;
import net.sf.anathema.platform.svgtree.document.components.VisualizableNode;
import org.dom4j.Element;

public class CreateElementForNode implements IVisualizableNodeVisitor {
  private final ILayer layer;
  private final Element cascadeElement;
  private final ITreePresentationProperties properties;

  public CreateElementForNode(ILayer layer, Element cascadeElement, ITreePresentationProperties properties) {
    this.layer = layer;
    this.cascadeElement = cascadeElement;
    this.properties = properties;
  }

  @Override
  public void visitHorizontalMetaNode(HorizontalMetaNode visitedNode) {
    throw new UnsupportedOperationException("Unroll Metanodes before creating XML.");
  }

  @Override
  public void visitSingleNode(VisualizableNode visitedNode) {
    new SvgNodeElementAdder(layer, visitedNode.getId(), visitedNode.getPosition(), properties.getNodeDimension()).toXML(
            cascadeElement);
  }

  @Override
  public void visitDummyNode(VisualizableDummyNode visitedNode) {
    //nothing to do
  }
}