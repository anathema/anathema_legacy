package net.sf.anathema.platform.svgtree.document.components;

import org.dom4j.Element;

public interface ILayer {

  void addNode(IVisualizableNode node);

  void setNodePosition(IVisualizableNode node, int centralPosition);

  void setFollowUp(ILayer layer);

  void setNodeOnNextFreePosition(IVisualizableNode node);

  void addNodesToXml(Element element);

  void positionNodes();

  IVisualizableNode[] getNodes();

  void addArrowsToXml(Element cascade);

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
}