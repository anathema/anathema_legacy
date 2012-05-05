package net.sf.anathema.platform.svgtree.document.components;

public interface ILayer {

  void addNode(IVisualizableNode node);

  void setNodePosition(IVisualizableNode node, int centralPosition);

  void setFollowUp(ILayer layer);

  void setNodeOnNextFreePosition(IVisualizableNode node);

  void positionNodes();

  IVisualizableNode[] getNodes();

  int getYPosition();

  int getWidth();

  void unrollHorizontalMetanodes();

  void shiftRight(int requiredShift);

  void shiftRightRecursivelyWithThreshold(int threshold, int requiredShift);

  int getOverlapFreePosition(IVisualizableNode node);

  Integer getPreviousNodeRightExtreme(IVisualizableNode node);

  Integer getNextNodeLeftExtreme(IVisualizableNode node);

  ILayer getPreviousLayer();

  void setPreviousLayer(ILayer layer);

  void positionNode(IVisualizableNode node);

  IVisualizableNode getPreviousNode(IVisualizableNode node);

  IVisualizableNode getNextNode(IVisualizableNode node);

  void moveNodeTo(IVisualizableNode node, int xPosition);

  boolean isBottomMostLayer();
}
