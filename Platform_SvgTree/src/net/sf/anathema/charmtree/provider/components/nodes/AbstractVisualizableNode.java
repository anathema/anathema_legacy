package net.sf.anathema.charmtree.provider.components.nodes;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.framework.magic.treelayout.nodes.ISimpleNode;
import net.sf.anathema.charmtree.provider.components.ILayer;

public abstract class AbstractVisualizableNode implements IVisualizableNode {

  private ILayer layer;
  private final Map<ISimpleNode, IVisualizableNode> map;
  private final Dimension charmDimension;
  private Integer xPosition;

  public AbstractVisualizableNode(Map<ISimpleNode, IVisualizableNode> map, Dimension charmDimension) {
    this.map = map;
    this.charmDimension = charmDimension;
  }

  public void setLayer(ILayer layer) {
    Ensure.ensureNull(this.layer);
    this.layer = layer;
  }

  public ILayer getLayer() {
    return layer;
  }

  public void shiftRightWithChildren(int requiredShift, IVisualizableNode[] excludedNodes) {
    // Note: Better implementation: Traverse the tree down to the children
    // Set children's new position
    // Recalculate all other positions using the given algorithms.
    List<IVisualizableNode> excludedNodeList = new ArrayList<IVisualizableNode>();
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

  public void shiftRight(int shift) {
    setPosition(Math.max(getPosition() + shift, layer.getOverlapFreePosition(this)));
  }

  protected final Map<ISimpleNode, IVisualizableNode> getContentNodeMap() {
    return map;
  }

  protected final Dimension getCharmDimension() {
    return charmDimension;
  }

  public Integer getLeftExtreme() {
    Integer extreme = Integer.MAX_VALUE;
    for (IVisualizableNode child : getChildren()) {
      extreme = Math.min(extreme, child.getLeftExtreme());
    }
    extreme = Math.min(extreme, getPosition() - getWidth() / 2);
    return extreme;
  }

  public Integer getRightExtreme() {
    Integer extreme = 0;
    for (IVisualizableNode child : getChildren()) {
      extreme = Math.max(extreme, child.getRightExtreme());
    }
    extreme = Math.max(extreme, getPosition() + getWidth() / 2);
    return extreme;
  }

  public int getHeight() {
    return charmDimension.height;
  }

  public void setPosition(int position) {
    xPosition = position;
  }

  public Integer getPosition() {
    return xPosition;
  }

  public IVisualizableNode[] getSharedChildren(IVisualizableNode otherNode) {
    List<IVisualizableNode> ownChildren = new ArrayList<IVisualizableNode>(Arrays.asList(getChildren()));
    List<IVisualizableNode> otherChildren = new ArrayList<IVisualizableNode>(Arrays.asList(otherNode.getChildren()));
    ownChildren.retainAll(otherChildren);
    return ownChildren.toArray(new IVisualizableNode[ownChildren.size()]);
  }

  public int getLeftSide() {
    return getPosition() - getWidth() / 2;
  }

  public int getRightSide() {
    return getPosition() + getWidth() / 2;
  }
}