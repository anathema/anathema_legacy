package net.sf.anathema.platform.tree.document.components;

import net.sf.anathema.framework.ui.Area;
import net.sf.anathema.graph.nodes.ISimpleNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class AbstractVisualizableNode implements IVisualizableNode {

  private ILayer layer;
  private final Map<ISimpleNode, IVisualizableNode> map;
  private final Area nodeDimension;
  private Integer xPosition;

  public AbstractVisualizableNode(Map<ISimpleNode, IVisualizableNode> map, Area nodeDimension) {
    this.map = map;
    this.nodeDimension = nodeDimension;
  }

  @Override
  public void setLayer(ILayer layer) {
    this.layer = layer;
  }

  @Override
  public ILayer getLayer() {
    return layer;
  }

  @Override
  public void shiftRightWithChildren(int requiredShift, IVisualizableNode[] excludedNodes) {
    // Note: Better implementation: Traverse the tree down to the children
    // Set children's new position
    // Recalculate all other positions using the given algorithms.
    List<IVisualizableNode> excludedNodeList = new ArrayList<>();
    if (excludedNodes != null) {
      Collections.addAll(excludedNodeList, excludedNodes);
    }
    shiftRight(requiredShift);
    for (IVisualizableNode node : getChildren()) {
      if (!excludedNodeList.contains(node)) {
        node.shiftRightWithChildren(requiredShift, null);
      }
    }
  }

  @Override
  public void shiftRight(int shift) {
    setPosition(Math.max(getPosition() + shift, layer.getOverlapFreePosition(this)));
  }

  @Override
  public void forceShiftRight(int shift) {
    setPosition(getPosition() + shift);

  }

  protected final Map<ISimpleNode, IVisualizableNode> getContentNodeMap() {
    return map;
  }

  protected final Area getNodeDimension() {
    return nodeDimension;
  }

  @Override
  public Integer getLeftExtreme() {
    Integer extreme = Integer.MAX_VALUE;
    for (IVisualizableNode child : getChildren()) {
      extreme = Math.min(extreme, child.getLeftExtreme());
    }
    extreme = Math.min(extreme, getPosition() - getWidth() / 2);
    return extreme;
  }

  @Override
  public Integer getRightExtreme() {
    Integer extreme = 0;
    for (IVisualizableNode child : getChildren()) {
      extreme = Math.max(extreme, child.getRightExtreme());
    }
    extreme = Math.max(extreme, getPosition() + getWidth() / 2);
    return extreme;
  }

  @Override
  public int getHeight() {
    return nodeDimension.height;
  }

  @Override
  public void setPosition(int position) {
    xPosition = position;
  }

  @Override
  public Integer getPosition() {
    return xPosition;
  }

  @Override
  public IVisualizableNode[] getSharedChildren(IVisualizableNode otherNode) {
    List<IVisualizableNode> ownChildren = new ArrayList<>(Arrays.asList(getChildren()));
    List<IVisualizableNode> otherChildren = new ArrayList<>(Arrays.asList(otherNode.getChildren()));
    ownChildren.retainAll(otherChildren);
    return ownChildren.toArray(new IVisualizableNode[ownChildren.size()]);
  }

  @Override
  public int getLeftSide() {
    return getPosition() - (getWidth() + 1) / 2;
  }

  @Override
  public int getRightSide() {
    return getPosition() + (getWidth() + 1) / 2;
  }
}
