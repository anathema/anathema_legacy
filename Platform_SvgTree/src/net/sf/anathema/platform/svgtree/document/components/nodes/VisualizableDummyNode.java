package net.sf.anathema.platform.svgtree.document.components.nodes;

import java.awt.Dimension;
import java.util.Map;

import net.sf.anathema.character.generic.framework.magic.treelayout.nodes.ISimpleNode;
import net.sf.anathema.lib.collection.MultiEntryMap;

import org.dom4j.Element;

public class VisualizableDummyNode extends AbstractSingleVisualizableNode {

  public void accept(IVisualizableNodeVisitor visitor) {
    visitor.visitDummyNode(this);
  }

  public VisualizableDummyNode(
      ISimpleNode contentNode,
      Map<ISimpleNode, IVisualizableNode> map,
      Dimension charmDimension,
      MultiEntryMap<ISimpleNode, ISimpleNode> leafNodesByAncestors) {
    super(contentNode, map, charmDimension, leafNodesByAncestors);
  }

  public void toXML(Element element) {
    // Nothing to do
  }
}