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
  private final Dimension lineDimension;
  private final MultiEntryMap<ISimpleNode, ISimpleNode> leafNodesByAncestors;

  public VisualizableNodeFactory(Dimension nodeDimension, Dimension gapDimension, Dimension lineDimension,
                                 Map<ISimpleNode, IVisualizableNode> visualizableNodesByContent,
                                 MultiEntryMap<ISimpleNode, ISimpleNode> leafNodesByAncestors) {
    this.nodeDimension = nodeDimension;
    this.gapDimension = gapDimension;
    this.lineDimension = lineDimension;
    this.map = visualizableNodesByContent;
    this.leafNodesByAncestors = leafNodesByAncestors;
  }

  public IVisualizableNode registerVisualizableNode(ISimpleNode contentNode) {
    IVisualizableNode node = createVisualizableNode(contentNode);
    map.put(contentNode, node);
    return node;
  }

  public void registerHorizontalMetaNode(Set<ISimpleNode> contentNodes) {
    HorizontalMetaNode metanode = new HorizontalMetaNode(map, nodeDimension, gapDimension);
    for (ISimpleNode node : contentNodes) {
      map.put(node, metanode);
      IVisualizableNode visualizableNode = createVisualizableNode(node);
      metanode.addInnerNode(node, visualizableNode);
    }
  }

  private IVisualizableNode createVisualizableNode(ISimpleNode contentNode) {
    if (contentNode instanceof IIdentifiedRegularNode) {
      return new VisualizableNode((IIdentifiedRegularNode) contentNode, map, nodeDimension, leafNodesByAncestors);
    } else {
      return new VisualizableDummyNode(contentNode, map, lineDimension, leafNodesByAncestors);
    }
  }
}