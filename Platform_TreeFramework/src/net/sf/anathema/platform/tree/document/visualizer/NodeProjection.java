package net.sf.anathema.platform.tree.document.visualizer;

import net.sf.anathema.platform.tree.document.components.ILayer;
import net.sf.anathema.platform.tree.document.components.IVisualizableNode;
import net.sf.anathema.platform.tree.document.components.VisualizableNodeLeftSideComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NodeProjection {

  private List<IVisualizableNode> nodeProjection = new ArrayList<>();

  public NodeProjection(ILayer... layers) {
    for (ILayer layer : layers) {
      Collections.addAll(nodeProjection, layer.getNodes());
    }
    Collections.sort(nodeProjection, new VisualizableNodeLeftSideComparator());
  }

  public void forceAllNodesOnTheSamePositionToTheLeft(IVisualizableNode node, int space) {
    int projectionIndex = getIndexOfFirstNodeOnSameHorizontalPosition(node);
    forceAllRemainingNodesLeft(projectionIndex, -space);
  }

  public void forceAllRemainingNodesLeft(int startIndex, int whiteSpace) {
    for (int moveNodeIndex = startIndex; moveNodeIndex < nodeProjection.size(); moveNodeIndex++) {
      nodeProjection.get(moveNodeIndex).forceShiftRight(-whiteSpace);
    }
  }

  public IVisualizableNode get(int index) {
    return nodeProjection.get(index);
  }

  public int size() {
    return nodeProjection.size();
  }

  public int getDistanceToPredecessors(int index) {
    IVisualizableNode node = get(index);
    return node.getLeftSide() - getRightmostSideUpTo(index - 1);
  }

  private int getIndexOfFirstNodeOnSameHorizontalPosition(IVisualizableNode nextNode) {
    int projectionIndex = indexOf(nextNode);
    while (projectionIndex > 0 && get(projectionIndex - 1).getLeftSide() == get(projectionIndex).getLeftSide()) {
      projectionIndex--;
    }
    return projectionIndex;
  }

  private int getRightmostSideUpTo(int index) {
    int maxRight = 0;
    for (int nodeIndex = 0; nodeIndex <= index; nodeIndex++) {
      maxRight = Math.max(maxRight, get(nodeIndex).getRightSide());
    }
    return maxRight;
  }

  public void print(String message) {
    System.out.println();
    System.out.println(message);
    for (IVisualizableNode node : nodeProjection) {
      System.out.println(
              node + ", left:" + node.getLeftSide() + ", x: " + node.getPosition() + ", right: " + node.getRightSide());
    }
  }

  public int indexOf(IVisualizableNode node) {
    return nodeProjection.indexOf(node);
  }

  public IVisualizableNode[] getNodes() {
    return nodeProjection.toArray(new IVisualizableNode[nodeProjection.size()]);
  }
}