package net.sf.anathema.platform.tree.document.components;

import java.util.Comparator;


public class VisualizableNodeLeftSideComparator implements Comparator<IVisualizableNode> {
  @Override
  public int compare(IVisualizableNode node1, IVisualizableNode node2) {
    return node1.getLeftSide() - node2.getLeftSide();
  }
}