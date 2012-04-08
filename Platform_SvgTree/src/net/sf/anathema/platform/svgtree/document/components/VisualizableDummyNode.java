package net.sf.anathema.platform.svgtree.document.components;

import net.sf.anathema.graph.nodes.ISimpleNode;
import net.sf.anathema.lib.collection.MultiEntryMap;
import org.dom4j.Element;

import java.awt.Dimension;
import java.util.Map;

public class VisualizableDummyNode extends AbstractSingleVisualizableNode {

  public void accept(final IVisualizableNodeVisitor visitor) {
    visitor.visitDummyNode(this);
  }

  public VisualizableDummyNode(
      final ISimpleNode contentNode,
      final Map<ISimpleNode, IVisualizableNode> map,
      final Dimension nodeDimension,
      final MultiEntryMap<ISimpleNode, ISimpleNode> leafNodesByAncestors) {
    super(contentNode, map, nodeDimension, leafNodesByAncestors);
  }

  public void toXML(final Element element) {
    // Nothing to do
  }

  public String toString() {
    return "Dummy";
  }
}
