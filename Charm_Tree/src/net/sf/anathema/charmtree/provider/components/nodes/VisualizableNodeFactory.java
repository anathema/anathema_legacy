package net.sf.anathema.charmtree.provider.components.nodes;

import java.awt.Dimension;
import java.util.Map;
import java.util.Set;

import net.sf.anathema.character.generic.framework.magic.treelayout.nodes.IIdentifiedRegularNode;
import net.sf.anathema.character.generic.framework.magic.treelayout.nodes.ISimpleNode;
import net.sf.anathema.lib.collection.MultiEntryMap;

public class VisualizableNodeFactory {

  private final Map<ISimpleNode, IVisualizableNode> map;
  private final Dimension charmDimension;
  private final Dimension gapDimension;
  private final MultiEntryMap<ISimpleNode, ISimpleNode> leafNodesByAncestors;

  public VisualizableNodeFactory(
      Dimension charmDimension,
      Dimension gapDimension,
      Map<ISimpleNode, IVisualizableNode> visualizableNodesByContent,
      MultiEntryMap<ISimpleNode, ISimpleNode> leafNodesByAncestors) {
    this.charmDimension = charmDimension;
    this.gapDimension = gapDimension;
    this.map = visualizableNodesByContent;
    this.leafNodesByAncestors = leafNodesByAncestors;
  }

  private IVisualizableNode createVisualizableNode(ISimpleNode contentNode) {
    IVisualizableNode node;
    if (contentNode instanceof IIdentifiedRegularNode) {
      node = new VisualizableNode((IIdentifiedRegularNode) contentNode, map, charmDimension, leafNodesByAncestors);
    }
    else {
      node = new VisualizableDummyNode(contentNode, map, charmDimension, leafNodesByAncestors);
    }
    return node;
  }

  public IVisualizableNode registerVisualizableNode(ISimpleNode contentNode) {
    IVisualizableNode node = createVisualizableNode(contentNode);
    map.put(contentNode, node);
    return node;
  }

  public void registerHorizontalMetaNode(Set<ISimpleNode> contentNodes) {
    HorizontalMetaNode metanode = new HorizontalMetaNode(map, charmDimension, gapDimension);
    for (ISimpleNode node : contentNodes) {
      map.put(node, metanode);
      IVisualizableNode visualizableNode = createVisualizableNode(node);
      metanode.addInnerNode(node, visualizableNode);
    }
  }
}