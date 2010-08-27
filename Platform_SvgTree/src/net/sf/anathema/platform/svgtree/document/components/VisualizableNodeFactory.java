package net.sf.anathema.platform.svgtree.document.components;

import java.awt.Dimension;
import java.util.Map;
import java.util.Set;

import net.sf.anathema.graph.nodes.IIdentifiedRegularNode;
import net.sf.anathema.graph.nodes.ISimpleNode;
import net.sf.anathema.lib.collection.MultiEntryMap;

public class VisualizableNodeFactory {

  private final Map<ISimpleNode, IVisualizableNode> map;
  private final Dimension nodeDimension;
  private final Dimension gapDimension;
  private final MultiEntryMap<ISimpleNode, ISimpleNode> leafNodesByAncestors;

  public VisualizableNodeFactory(
      final Dimension nodeDimension,
      final Dimension gapDimension,
      final Map<ISimpleNode, IVisualizableNode> visualizableNodesByContent,
      final MultiEntryMap<ISimpleNode, ISimpleNode> leafNodesByAncestors) {
    this.nodeDimension = nodeDimension;
    this.gapDimension = gapDimension;
    this.map = visualizableNodesByContent;
    this.leafNodesByAncestors = leafNodesByAncestors;
  }

  private IVisualizableNode createVisualizableNode(final ISimpleNode contentNode) {
    IVisualizableNode node;
    if (contentNode instanceof IIdentifiedRegularNode) {
      node = new VisualizableNode((IIdentifiedRegularNode) contentNode, map, nodeDimension, leafNodesByAncestors);
    }
    else {
      node = new VisualizableDummyNode(contentNode, map, nodeDimension, leafNodesByAncestors);
    }
    return node;
  }

  public IVisualizableNode registerVisualizableNode(final ISimpleNode contentNode) {
    IVisualizableNode node = createVisualizableNode(contentNode);
    map.put(contentNode, node);
    return node;
  }

  public void registerHorizontalMetaNode(final Set<ISimpleNode> contentNodes) {
    HorizontalMetaNode metanode = new HorizontalMetaNode(map, nodeDimension, gapDimension);
    for (ISimpleNode node : contentNodes) {
      map.put(node, metanode);
      IVisualizableNode visualizableNode = createVisualizableNode(node);
      metanode.addInnerNode(node, visualizableNode);
    }
  }
}