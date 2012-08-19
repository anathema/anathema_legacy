package net.sf.anathema.platform.svgtree.document.components;

public interface IVisualizableNode {

  /**
   * Return central Position of rightmost Node in the subtree
   */
  Integer getRightExtreme();

  void setLayer(ILayer layer);

  Integer getLeftExtreme();

  void shiftRightWithChildren(int requiredShift, IVisualizableNode[] excludedNodes);

  int getWidth();

  boolean isOfSameLeafGroup(IVisualizableNode node);

  void accept(IVisualizableNodeVisitor visitor);

  IVisualizableNode[] getChildren();

  int getHeight();

  void setPosition(int position);

  Integer getPosition();

  IVisualizableNode[] getSharedChildren(IVisualizableNode otherNode);

  void shiftRight(int requiredShift);

  void forceShiftRight(int requiredShift);

  ILayer getLayer();

  int getLeftSide();

  int getRightSide();

  boolean isRootNode();

  IVisualizableNode[] getParents();
}
