package net.sf.anathema.platform.svgtree.graph.nodes;

import net.sf.anathema.platform.svgtree.graph.ordering.IStructureNode;

public interface ISimpleNode extends IStructureNode {

  public ISimpleNode[] getChildren();

  public int getLayer();

  public ISimpleNode[] getParents();

  public boolean isRootNode();

  public boolean isLeafNode();

  public void removeParent(ISimpleNode node);

  public void addParent(ISimpleNode newNode);

  public void reorderChildren(ISimpleNode[] orderedNodes);

  ISimpleNode[] getChildren(ISimpleNode[] childrenLayer);
}