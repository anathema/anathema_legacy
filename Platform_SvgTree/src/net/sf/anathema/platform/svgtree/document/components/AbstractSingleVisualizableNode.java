package net.sf.anathema.platform.svgtree.document.components;

import net.sf.anathema.graph.nodes.ISimpleNode;
import net.sf.anathema.lib.collection.MultiEntryMap;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class AbstractSingleVisualizableNode extends AbstractVisualizableNode {

  private final ISimpleNode content;
  private final MultiEntryMap<ISimpleNode, ISimpleNode> leafNodesByAncestors;

  public AbstractSingleVisualizableNode(
      ISimpleNode content,
      Map<ISimpleNode, IVisualizableNode> map,
      Dimension nodeDimension,
      MultiEntryMap<ISimpleNode, ISimpleNode> leafNodesByAncestors) {
    super(map, nodeDimension);
    this.content = content;
    this.leafNodesByAncestors = leafNodesByAncestors;
  }

  @Override
  public IVisualizableNode[] getChildren() {
    List<IVisualizableNode> childrenList = new ArrayList<IVisualizableNode>();
    for (ISimpleNode child : content.getChildren()) {
      childrenList.add(getContentNodeMap().get(child));
    }
    return childrenList.toArray(new IVisualizableNode[childrenList.size()]);
  }

  @Override
  public IVisualizableNode[] getParents() {
    List<IVisualizableNode> parentList = new ArrayList<IVisualizableNode>();
    for (ISimpleNode parent : content.getParents()) {
      parentList.add(getContentNodeMap().get(parent));
    }
    return parentList.toArray(new IVisualizableNode[parentList.size()]);
  }

  @Override
  public boolean isRootNode() {
    return content.isRootNode();
  }

  protected ISimpleNode getContentNode() {
    return content;
  }

  @Override
  public int getWidth() {
    return getNodeDimension().width;
  }

  @Override
  public boolean isOfSameLeafGroup(IVisualizableNode node) {
    final boolean[] result = new boolean[1];
    node.accept(new IVisualizableNodeVisitor() {

      @Override
      public void visitSingleNode(VisualizableNode visitedNode) {
        result[0] = visitAbstractSingleNode(visitedNode);
      }

      @Override
      public void visitHorizontalMetaNode(HorizontalMetaNode visitedNode) {
        result[0] = visitedNode.isOfSameLeafGroup(AbstractSingleVisualizableNode.this);
      }

      @Override
      public void visitDummyNode(VisualizableDummyNode visitedNode) {
        result[0] = visitAbstractSingleNode(visitedNode);
      }
    });
    return result[0];
  }

  private boolean visitAbstractSingleNode(AbstractSingleVisualizableNode visitedNode) {
    ISimpleNode otherContent = visitedNode.getContentNode();
    List<ISimpleNode> otherLeaves = leafNodesByAncestors.get(otherContent);
    List<ISimpleNode> thisLeaves = leafNodesByAncestors.get(content);
    return !Collections.disjoint(otherLeaves, thisLeaves);
  }
}