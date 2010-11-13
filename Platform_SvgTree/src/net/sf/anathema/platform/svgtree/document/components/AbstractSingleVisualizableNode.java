package net.sf.anathema.platform.svgtree.document.components;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import net.sf.anathema.graph.nodes.ISimpleNode;
import net.sf.anathema.lib.collection.MultiEntryMap;

public abstract class AbstractSingleVisualizableNode extends AbstractVisualizableNode {

  private final ISimpleNode content;
  private final MultiEntryMap<ISimpleNode, ISimpleNode> leafNodesByAncestors;

  public AbstractSingleVisualizableNode(
      final ISimpleNode content,
      final Map<ISimpleNode, IVisualizableNode> map,
      final Dimension nodeDimension,
      final MultiEntryMap<ISimpleNode, ISimpleNode> leafNodesByAncestors) {
    super(map, nodeDimension);
    this.content = content;
    this.leafNodesByAncestors = leafNodesByAncestors;
  }

  public IVisualizableNode[] getChildren() {
    List<IVisualizableNode> childrenList = new ArrayList<IVisualizableNode>();
    for (ISimpleNode child : content.getChildren()) {
      childrenList.add(getContentNodeMap().get(child));
    }
    return childrenList.toArray(new IVisualizableNode[childrenList.size()]);
  }

  public IVisualizableNode[] getParents() {
    List<IVisualizableNode> parentList = new ArrayList<IVisualizableNode>();
    for (ISimpleNode parent : content.getParents()) {
      parentList.add(getContentNodeMap().get(parent));
    }
    return parentList.toArray(new IVisualizableNode[parentList.size()]);
  }

  public boolean isRootNode() {
    return content.isRootNode();
  }

  protected ISimpleNode getContentNode() {
    return content;
  }

  public int getWidth() {
    return getNodeDimension().width;
  }

  public boolean isOfSameLeafGroup(final IVisualizableNode node) {
    final boolean[] result = new boolean[1];
    node.accept(new IVisualizableNodeVisitor() {

      public void visitSingleNode(final VisualizableNode visitedNode) {
        result[0] = visitAbstractSingleNode(visitedNode);
      }

      public void visitHorizontalMetaNode(final HorizontalMetaNode visitedNode) {
        result[0] = visitedNode.isOfSameLeafGroup(AbstractSingleVisualizableNode.this);
      }

      public void visitDummyNode(final VisualizableDummyNode visitedNode) {
        result[0] = visitAbstractSingleNode(visitedNode);
      }
    });
    return result[0];
  }

  private boolean visitAbstractSingleNode(final AbstractSingleVisualizableNode visitedNode) {
    ISimpleNode otherContent = visitedNode.getContentNode();
    List<ISimpleNode> otherLeaves = leafNodesByAncestors.get(otherContent);
    List<ISimpleNode> thisLeaves = leafNodesByAncestors.get(content);
    return !Collections.disjoint(otherLeaves, thisLeaves);
  }
}