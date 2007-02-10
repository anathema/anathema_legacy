package net.sf.anathema.charmtree.provider.components;

import java.util.Comparator;

import net.sf.anathema.charmtree.provider.components.nodes.IVisualizableNode;

public final class VisualizableNodePositionComparator implements Comparator<IVisualizableNode> {
  public int compare(IVisualizableNode node1, IVisualizableNode node2) {
    return node1.getPosition() - node2.getPosition();
  }
}