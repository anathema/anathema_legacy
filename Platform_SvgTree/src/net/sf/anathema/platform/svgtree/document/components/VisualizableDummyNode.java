package net.sf.anathema.platform.svgtree.document.components;

import net.sf.anathema.graph.nodes.ISimpleNode;
import net.sf.anathema.lib.collection.MultiEntryMap;
import org.dom4j.Element;

import java.awt.Dimension;
import java.util.Map;

public class VisualizableDummyNode extends AbstractSingleVisualizableNode {

  @Override
  public void accept(IVisualizableNodeVisitor visitor) {
    visitor.visitDummyNode(this);
  }

  public VisualizableDummyNode(
      ISimpleNode contentNode,
      Map<ISimpleNode, IVisualizableNode> map,
      Dimension nodeDimension,
      MultiEntryMap<ISimpleNode, ISimpleNode> leafNodesByAncestors) {
    super(contentNode, map, nodeDimension, leafNodesByAncestors);
  }

  @Override
  public void toXML(Element element) {
    // Nothing to do
  }

  public String toString() {
    return "Dummy";
  }
}
