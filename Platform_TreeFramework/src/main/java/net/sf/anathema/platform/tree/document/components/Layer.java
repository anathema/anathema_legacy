package net.sf.anathema.platform.tree.document.components;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import net.sf.anathema.framework.ui.Area;
import net.sf.anathema.platform.tree.document.util.BackwardsIterable;

import java.util.ArrayList;
import java.util.List;

public class Layer implements ILayer {

  protected final List<IVisualizableNode> nodes = new ArrayList<>();
  private ILayer nextLayer;
  private final Area gapDimension;
  private final int yPosition;
  private ILayer previousLayer;

  public Layer(Area gapDimension, int yPosition) {
    this.gapDimension = gapDimension;
    this.yPosition = yPosition;
  }

  @Override
  public void setPreviousLayer(ILayer previousLayer) {
    this.previousLayer = previousLayer;
  }

  @Override
  public boolean isBottomMostLayer() {
    return nextLayer == null;
  }

  @Override
  public void addNode(IVisualizableNode node) {
    if (nodes.contains(node)) {
      return;
    }
    nodes.add(node);
    node.setLayer(this);
  }

  @Override
  public void positionNodes() {
    for (IVisualizableNode node : nodes) {
      positionNode(node);
    }
  }

  @Override
  public void positionNode(IVisualizableNode node) {
    IVisualizableNode[] children = node.getChildren();
    if (children.length == 0) {
      setNodeOnNextFreePosition(node);
      return;
    }
    IVisualizableNode firstChild = findFirstOrderedChild(children);
    IVisualizableNode lastChild = findLastOrderedChild(children);
    int childPositionSum = 0;
    childPositionSum += firstChild.getLeftSide();
    childPositionSum += lastChild.getRightSide();
    int averageChildPosition = childPositionSum / 2;
    setNodePosition(node, averageChildPosition);
  }

  private IVisualizableNode findFirstOrderedChild(IVisualizableNode[] children) {
    IVisualizableNode[] nodesByLayer = nextLayer.getNodes();
    for (IVisualizableNode node : nodesByLayer) {
      for (IVisualizableNode child : children) {
        if (node == child) {
          return child;
        }
      }
    }
    throw new IllegalArgumentException("Children not found on layer " + nextLayer);
  }

  @Override
  public IVisualizableNode[] getNodes() {
    return nodes.toArray(new IVisualizableNode[nodes.size()]);
  }

  private IVisualizableNode findLastOrderedChild(IVisualizableNode[] children) {
    List<IVisualizableNode> remainingChildren = Lists.newArrayList(children);
    IVisualizableNode[] nodesByLayer = nextLayer.getNodes();
    for (IVisualizableNode node : nodesByLayer) {
      for (IVisualizableNode child : children) {
        if (node == child) {
          remainingChildren.remove(child);
        }
        if (remainingChildren.isEmpty()) {
          return child;
        }
      }
    }
    throw new IllegalArgumentException("Children not found on layer " + nextLayer);
  }

  @Override
  public void setNodePosition(IVisualizableNode node, int position) {
    node.setPosition(position);
    IVisualizableNode previousNode = getPreviousNode(node);
    if (previousNode == null) {
      correctLeftBorder(node);
      return;
    }
    boolean sameLeafGroup = previousNode.isOfSameLeafGroup(node);
    Integer currentNodeLeftSide;
    Integer previousNodeRightSide;
    if (sameLeafGroup) {
      previousNodeRightSide = previousNode.getRightSide();
      currentNodeLeftSide = node.getLeftSide();
      IVisualizableNode[] sharedChildren = node.getSharedChildren(previousNode);
      int requiredShift = previousNodeRightSide + gapDimension.width - currentNodeLeftSide;
      if (requiredShift <= 0) {
        return;
      }
      node.shiftRightWithChildren(requiredShift, sharedChildren);
    } else {
      previousNodeRightSide = getPreviousNodeRightExtreme(node);
      currentNodeLeftSide = node.getLeftExtreme();
      int requiredShift = previousNodeRightSide + gapDimension.width - currentNodeLeftSide;
      if (requiredShift <= 0) {
        return;
      }
      // Revert to ShiftRightWithChildren? Error in "DetermineLeafGroup"?
      node.shiftRight(requiredShift);
      if (nextLayer != null) {
        nextLayer.shiftRightRecursivelyWithThreshold(currentNodeLeftSide, requiredShift);
      }
    }
  }

  private void correctLeftBorder(IVisualizableNode node) {
    int leftSideOverlap = 0 - node.getLeftExtreme();
    if (leftSideOverlap > 0) {
      node.shiftRight(leftSideOverlap);
      nextLayer.shiftRight(leftSideOverlap);
    }
  }

  @Override
  public void setNodeOnNextFreePosition(IVisualizableNode node) {
    Integer previousNodeRightSide = getPreviousNodeRightExtreme(node);
    Integer newPosition = calculateNextFreePosition(previousNodeRightSide, node);
    setNodePosition(node, newPosition);
  }

  @Override
  public int getOverlapFreePosition(IVisualizableNode node) {
    IVisualizableNode previousNode = getPreviousNode(node);
    if (previousNode == null) {
      return calculateNextFreePosition(null, node);
    }
    return calculateNextFreePosition(previousNode.getRightSide(), node);
  }

  @Override
  public Integer getNextNodeLeftExtreme(IVisualizableNode node) {
    IVisualizableNode nextNode = getNextNode(node);
    if (nextNode == null) {
      return null;
    }
    return nextNode.getLeftExtreme();
  }

  @Override
  public IVisualizableNode getNextNode(IVisualizableNode node) {
    int index = nodes.indexOf(node);
    if (index == nodes.size() - 1) {
      return null;
    }
    return nodes.get(index + 1);
  }

  @Override
  public void moveNodeTo(IVisualizableNode node, int xPosition) {
    int shift = xPosition - node.getPosition();
    if (shift < 0) {
      getRoomToShiftLeft(getIndexAfter(xPosition));
      int moveStartIndex = getIndexAfter(xPosition);
      int roomToShift = getRoomToShiftLeft(moveStartIndex);
      if (roomToShift >= -shift) {
        for (int index = moveStartIndex; index < nodes.indexOf(node) + 1; index++) {
          nodes.get(index).forceShiftRight(shift);
        }
      } else {
        for (int index = moveStartIndex; index < nodes.indexOf(node); index++) {
          nodes.get(index).forceShiftRight(-roomToShift);
        }
        node.setPosition(xPosition);
      }
    } else {
      for (int index = nodes.indexOf(node); index < nodes.size(); index++) {
        nodes.get(index).forceShiftRight(shift);
      }
    }
  }

  private int getRoomToShiftLeft(int index) {
    if (index == 0) {
      return nodes.get(index).getLeftSide();
    }
    return nodes.get(index).getPosition() - nodes.get(index - 1).getPosition();
  }

  private int getIndexAfter(int xPosition) {
    for (int index = 0; index < nodes.size(); index++) {
      if (nodes.get(index).getPosition() >= xPosition) {
        return index;
      }
    }
    throw new IllegalStateException();
  }

  @Override
  public Integer getPreviousNodeRightExtreme(IVisualizableNode node) {
    IVisualizableNode previousNode = getPreviousNode(node);
    if (previousNode == null) {
      return null;
    }
    return previousNode.getRightExtreme();
  }

  @Override
  public IVisualizableNode getPreviousNode(IVisualizableNode node) {
    int index = nodes.indexOf(node);
    if (index == 0) {
      return null;
    }
    return nodes.get(index - 1);
  }

  protected Integer calculateNextFreePosition(Integer lastNodeRightSide, IVisualizableNode node) {
    if (lastNodeRightSide == null) {
      return (node.getWidth() + 1) / 2;
    }
    return lastNodeRightSide + gapDimension.width + (node.getWidth() + 1) / 2;
  }

  @Override
  public void setFollowUp(ILayer layer) {
    Preconditions.checkNotNull(layer);
    Preconditions.checkArgument(nextLayer == null);
    this.nextLayer = layer;
  }

  @Override
  public int getYPosition() {
    return yPosition;
  }

  @Override
  public int getWidth() {
    int width = 0;
    for (IVisualizableNode node : nodes) {
      width = Math.max(width, node.getRightSide());
    }
    return width;
  }

  @Override
  public void unrollHorizontalMetanodes() {
    for (IVisualizableNode node : new ArrayList<>(nodes)) {
      node.accept(new IVisualizableNodeVisitor() {
        @Override
        public void visitHorizontalMetaNode(HorizontalMetaNode visitedNode) {
          int index = nodes.indexOf(visitedNode);
          IVisualizableNode[] innerNodes = visitedNode.getInnerNodes();
          for (IVisualizableNode innerNode : new BackwardsIterable<>(innerNodes)) {
            nodes.add(index, innerNode);
          }
          visitedNode.resolveMetanode();
          nodes.remove(visitedNode);
        }

        @Override
        public void visitSingleNode(VisualizableNode visitedNode) {
          // Nothing to do
        }

        @Override
        public void visitDummyNode(VisualizableDummyNode visitedNode) {
          // Nothing to do
        }
      });
    }
  }

  @Override
  public void shiftRight(int requiredShift) {
    for (IVisualizableNode node : nodes) {
      node.shiftRight(requiredShift);
    }
  }

  @Override
  public void shiftRightRecursivelyWithThreshold(int threshold, int requiredShift) {
    for (IVisualizableNode node : nodes) {
      if (node.getPosition() >= threshold) {
        node.shiftRight(requiredShift);
      }
    }
    if (nextLayer != null) {
      nextLayer.shiftRightRecursivelyWithThreshold(threshold, requiredShift);
    }
  }

  @Override
  public ILayer getPreviousLayer() {
    return previousLayer;
  }
}
