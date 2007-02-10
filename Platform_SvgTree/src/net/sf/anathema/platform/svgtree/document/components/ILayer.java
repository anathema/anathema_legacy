package net.sf.anathema.platform.svgtree.document.components;

import net.sf.anathema.platform.svgtree.document.components.nodes.IVisualizableNode;

import org.dom4j.Element;

public interface ILayer {

  public void addNode(IVisualizableNode node);

  public void setNodePosition(IVisualizableNode node, int centralPosition);

  public void setFollowUp(ILayer layer);

  public ILayer getNextLayer();

  public void setNodeOnNextFreePosition(IVisualizableNode node);

  public void addNodesToXml(Element element);

  public void setNodePositionWithoutChecking(IVisualizableNode node, int position);

  public void positionNodes();

  public IVisualizableNode[] getNodes();

  public void addArrowsToXml(Element cascade);

  public int getYPosition();

  public int getWidth();

  public void unrollHorizontalMetanodes();

  public void shiftRight(int requiredShift);

  public void shiftRightRecursivelyWithThreshold(int threshold, int requiredShift);

  public int getOverlapFreePosition(IVisualizableNode node);

  public Integer getPreviousNodeRightExtreme(IVisualizableNode node);

  public Integer getNextNodeLeftExtreme(IVisualizableNode node);

  public ILayer getPreviousLayer();

  public void setPreviousLayer(ILayer layer);

  public void positionNode(IVisualizableNode node);

  public IVisualizableNode getPreviousNode(IVisualizableNode node);

  public IVisualizableNode getNextNode(IVisualizableNode node);
}