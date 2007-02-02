package net.sf.anathema.character.generic.framework.magic.treelayout.nodes;

import net.sf.anathema.character.generic.framework.magic.treelayout.ordering.leaf.IStructureNode;

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