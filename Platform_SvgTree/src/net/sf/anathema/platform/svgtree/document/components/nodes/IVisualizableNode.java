package net.sf.anathema.platform.svgtree.document.components.nodes;

import net.sf.anathema.platform.svgtree.document.components.ILayer;

import org.dom4j.Element;

public interface IVisualizableNode {

  /**
   * Return central Position of rightmost Node in the subtree
   */
  public Integer getRightExtreme();

  public void toXML(Element element);

  public void setLayer(ILayer layer);

  public Integer getLeftExtreme();

  public void shiftRightWithChildren(int requiredShift, IVisualizableNode[] excludedNodes);

  public int getWidth();

  public boolean isOfSameLeafGroup(IVisualizableNode node);

  public void accept(IVisualizableNodeVisitor visitor);

  public IVisualizableNode[] getChildren();

  public int getHeight();

  public void setPosition(int position);

  public Integer getPosition();

  public IVisualizableNode[] getSharedChildren(IVisualizableNode otherNode);

  public void shiftRight(int requiredShift);

  public ILayer getLayer();

  public int getLeftSide();

  public int getRightSide();

  public boolean isRootNode();

  public IVisualizableNode[] getParents();
}