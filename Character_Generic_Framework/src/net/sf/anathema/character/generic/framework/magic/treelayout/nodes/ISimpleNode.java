package net.sf.anathema.character.generic.framework.magic.treelayout.nodes;

public interface ISimpleNode {

  public ISimpleNode[] getChildren();

  public int getLayer();

  public ISimpleNode[] getParents();

  public boolean isRootNode();

  public boolean isLeafNode();

  public void removeParent(ISimpleNode node);

  public void addParent(ISimpleNode newNode);
}