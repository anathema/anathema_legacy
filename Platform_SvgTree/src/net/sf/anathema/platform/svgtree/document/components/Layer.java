package net.sf.anathema.platform.svgtree.document.components;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.platform.svgtree.document.svg.PolylineSVGArrow;
import net.sf.anathema.platform.svgtree.document.util.BackwardsIterable;

import org.dom4j.Element;

public class Layer implements ILayer {

  private final List<IVisualizableNode> nodes = new ArrayList<IVisualizableNode>();
  private ILayer nextLayer;
  private final Dimension gapDimension;
  private final int yPosition;
  private ILayer previousLayer;

  public Layer(Dimension gapDimension, int yPosition) {
    this.gapDimension = gapDimension;
    this.yPosition = yPosition;
  }

  public void setPreviousLayer(ILayer previousLayer) {
    this.previousLayer = previousLayer;
  }

  public void addNode(IVisualizableNode node) {
    if (nodes.contains(node)) {
      return;
    }
    nodes.add(node);
    node.setLayer(this);
  }

  public void positionNodes() {
    for (IVisualizableNode node : nodes) {
      positionNode(node);
    }
  }

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
    throw new IllegalArgumentException("Children not found on layer " + nextLayer); //$NON-NLS-1$
  }

  public IVisualizableNode[] getNodes() {
    return nodes.toArray(new IVisualizableNode[nodes.size()]);
  }

  private IVisualizableNode findLastOrderedChild(IVisualizableNode[] children) {
    List<IVisualizableNode> remainingChildren = new ArrayList<IVisualizableNode>(Arrays.asList(children));
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
    throw new IllegalArgumentException("Children not found on layer " + nextLayer); //$NON-NLS-1$
  }

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
    IVisualizableNode[] sharedChildren = null;
    if (sameLeafGroup) {
      previousNodeRightSide = previousNode.getRightSide();
      currentNodeLeftSide = node.getLeftSide();
      sharedChildren = node.getSharedChildren(previousNode);
      int requiredShift = previousNodeRightSide + gapDimension.width - currentNodeLeftSide;
      if (requiredShift <= 0) {
        return;
      }
      node.shiftRightWithChildren(requiredShift, sharedChildren);
    }
    else {
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

  public void setNodePositionWithoutChecking(IVisualizableNode node, int position) {
    node.setPosition(position);
  }

  public void setNodeOnNextFreePosition(IVisualizableNode node) {
    Integer previousNodeRightSide = getPreviousNodeRightExtreme(node);
    Integer newPosition = calculateNextFreePosition(previousNodeRightSide, node);
    setNodePosition(node, newPosition);
  }

  public int getOverlapFreePosition(IVisualizableNode node) {
    IVisualizableNode previousNode = getPreviousNode(node);
    if (previousNode == null) {
      return calculateNextFreePosition(null, node);
    }
    return calculateNextFreePosition(previousNode.getRightSide(), node);
  }

  public Integer getNextNodeLeftExtreme(IVisualizableNode node) {
    IVisualizableNode nextNode = getNextNode(node);
    if (nextNode == null) {
      return null;
    }
    return nextNode.getLeftExtreme();
  }

  public IVisualizableNode getNextNode(IVisualizableNode node) {
    int index = nodes.indexOf(node);
    if (index == nodes.size() - 1) {
      return null;
    }
    return nodes.get(index + 1);
  }

  public Integer getPreviousNodeRightExtreme(IVisualizableNode node) {
    IVisualizableNode previousNode = getPreviousNode(node);
    if (previousNode == null) {
      return null;
    }
    return previousNode.getRightExtreme();
  }

  public IVisualizableNode getPreviousNode(IVisualizableNode node) {
    int index = nodes.indexOf(node);
    if (index == 0) {
      return null;
    }
    return nodes.get(index - 1);
  }

  private Integer calculateNextFreePosition(Integer lastNodeRightSide, IVisualizableNode node) {
    if (lastNodeRightSide == null) {
      return node.getWidth() / 2;
    }
    return lastNodeRightSide + gapDimension.width + node.getWidth() / 2;
  }

  public void setFollowUp(ILayer layer) {
    Ensure.ensureNotNull(layer);
    Ensure.ensureNull(nextLayer);
    this.nextLayer = layer;
  }

  public ILayer getNextLayer() {
    return nextLayer;
  }

  public void addNodesToXml(Element element) {
    for (IVisualizableNode node : nodes) {
      node.toXML(element);
    }
  }

  public void addArrowsToXml(final Element cascade) {
    if (nextLayer == null) {
      return;
    }
    for (IVisualizableNode node : nodes) {
      node.accept(new IVisualizableNodeVisitor() {
        public void visitHorizontalMetaNode(HorizontalMetaNode visitedNode) {
          throw new IllegalStateException("Metanodes must be resolved before arrows are created."); //$NON-NLS-1$
        }

        public void visitSingleNode(VisualizableNode visitedNode) {
          for (IVisualizableNode child : visitedNode.getChildren()) {
            PolylineSVGArrow arrow = new PolylineSVGArrow();
            arrow.addPoint(visitedNode.getPosition(), yPosition + visitedNode.getHeight());
            arrow.addPoint(child.getPosition(), child.getLayer().getYPosition());
            extendArrow(arrow, child);
            cascade.add(arrow.toXML());
          }
        }

        public void visitDummyNode(VisualizableDummyNode visitedNode) {
          // Nothing to do
        }
      });
    }
  }

  private void extendArrow(final PolylineSVGArrow arrow, IVisualizableNode node) {
    node.accept(new IVisualizableNodeVisitor() {
      public void visitHorizontalMetaNode(HorizontalMetaNode visitedNode) {
        throw new IllegalStateException("Metanodes must be resolved before arrows are created."); //$NON-NLS-1$
      }

      public void visitSingleNode(VisualizableNode visitedNode) {
        // Nothing to do
      }

      public void visitDummyNode(VisualizableDummyNode visitedNode) {
        IVisualizableNode child = visitedNode.getChildren()[0];
        arrow.addPoint(visitedNode.getPosition(), visitedNode.getLayer().getYPosition() + visitedNode.getHeight());
        arrow.addPoint(child.getPosition(), child.getLayer().getYPosition());
        extendArrow(arrow, child);
      }
    });
  }

  public int getYPosition() {
    return yPosition;
  }

  public int getWidth() {
    IVisualizableNode finalNode = nodes.get(nodes.size() - 1);
    if (finalNode == null) {
      return 0;
    }
    return finalNode.getRightSide();
  }

  public void unrollHorizontalMetanodes() {
    for (IVisualizableNode node : new ArrayList<IVisualizableNode>(nodes)) {
      node.accept(new IVisualizableNodeVisitor() {
        public void visitHorizontalMetaNode(HorizontalMetaNode visitedNode) {
          int index = nodes.indexOf(visitedNode);
          IVisualizableNode[] innerNodes = visitedNode.getInnerNodes();
          for (IVisualizableNode innerNode : new BackwardsIterable<IVisualizableNode>(innerNodes)) {
            nodes.add(index, innerNode);
          }
          visitedNode.resolveMetanode();
          nodes.remove(visitedNode);
        }

        public void visitSingleNode(VisualizableNode visitedNode) {
          // Nothing to do
        }

        public void visitDummyNode(VisualizableDummyNode visitedNode) {
          // Nothing to do
        }
      });
    }
  }

  public void shiftRight(int requiredShift) {
    for (IVisualizableNode node : nodes) {
      node.shiftRight(requiredShift);
    }
  }

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

  public ILayer getPreviousLayer() {
    return previousLayer;
  }
}