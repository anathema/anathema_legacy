package net.sf.anathema.platform.svgtree.document.components;

import java.awt.Dimension;
import java.util.Map;

import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.platform.svgtree.graph.nodes.ISimpleNode;

import org.dom4j.Element;

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
}