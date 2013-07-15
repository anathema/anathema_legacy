package net.sf.anathema.graph.nodes;

import net.sf.anathema.graph.ordering.IStructureNode;

public interface ISimpleNode extends IStructureNode {

  ISimpleNode[] getChildren();

  int getLayer();

  ISimpleNode[] getParents();

  boolean isRootNode();

  @Override
  boolean isLeafNode();

  void removeParent(ISimpleNode node);

  void addParent(ISimpleNode newNode);

  void reorderChildren(ISimpleNode[] orderedNodes);

  ISimpleNode[] getChildren(ISimpleNode[] childrenLayer);
}