package net.sf.anathema.platform.svgtree.document.components;

import java.awt.Dimension;
import java.util.Arrays;
import java.util.Comparator;

public class PriorityLayer extends Layer {

	// Implements the basic steps behind Sugiyama's priority layout method,
	// as described in his Graph Drawing and Applications (2002).

	public PriorityLayer(Dimension gapDimension, int yPosition) {
		super(gapDimension, yPosition);
	}
	
	private static boolean compareIntegers(Integer a, Integer b) {
	  return a != null ? a.equals(b) : b == null;
	}
	
	public void checkOverlaps() {
		for (int i = 0; i < nodes.size() - 1; i++) {
			assert compareIntegers(getRightSideSlack(nodes.get(i)), getLeftSideSlack(nodes.get(i + 1))) : "Bad slack calculation";
			if (getRightSideSlack(nodes.get(i)) < 0) {
				String nodeID = "stand-in";
				String nextNodeID = "stand-in";
				if (nodes.get(i) instanceof VisualizableNode) {
					nodeID = ((VisualizableNode)nodes.get(i)).getContentNode().getId();
				}
				if (nodes.get(i + 1) instanceof VisualizableNode) {
					nextNodeID = ((VisualizableNode)nodes.get(i + 1)).getContentNode().getId();
				}
				System.out.println("Slack between " + nodeID + " and " + nextNodeID + " = " + getRightSideSlack(nodes.get(i)));
				
			}
		}
	}

	public void positionNode(IVisualizableNode node) {
		setNodeOnNextFreePositionWithoutChecking(node);
	}
	
	private Integer barycenter(IVisualizableNode[] nodes) {
		if (nodes.length == 0) return null;
		
		int sum = 0;
		for (IVisualizableNode node : nodes) {
			sum += node.getPosition();
		}
		return Math.round(((float)sum)/nodes.length);
	}
	
	public void repositionNodes(Direction direction) {
		if (direction == Direction.UP) {
			System.out.println("***** Sweeping UP *****");
		}
		else {
			System.out.println("***** Sweeping DOWN *****");
		}
		IVisualizableNode[] prioritizedNodes = getNodes();
		Comparator<IVisualizableNode> comparator = new PriorityNodeComparator(direction);
		Arrays.sort(prioritizedNodes, comparator);
		
		for (IVisualizableNode node : prioritizedNodes) {
			Integer targetPosition;
			if (direction == Direction.DOWN) {
				targetPosition = barycenter(node.getParents());
			}
			else {
				targetPosition = barycenter(node.getChildren());
			}
			
			if (targetPosition != null) {
				String nodeId = "stand-in";
				if (node instanceof VisualizableNode) {
					nodeId = ((VisualizableNode) node).getContentNode().getId();
				}
				System.out.println(nodeId + ": " + node.getPosition() + " --> " + targetPosition);
				setNodePosition(node, targetPosition, comparator);
				System.out.println("Reached " + node.getPosition());
			}
		}
	}

	public void setNodePosition(final IVisualizableNode node, int centralPosition) {
		setNodePosition(node, centralPosition, new Comparator<IVisualizableNode>(){
			@Override
			public int compare(IVisualizableNode arg0, IVisualizableNode arg1) {
				if (arg0 != node && arg1 == node) {
					return -1;
				}
				else if (arg0 == node && arg1 != node) {
					return 1;
				}
				else {
					return 0;
				}
			}
		});
	}

	public void setNodePosition(IVisualizableNode node, int position, Comparator<? super IVisualizableNode> priorityComparator) {
		if (position == node.getPosition()) return;
		
		int targetShift = Math.abs(position - node.getPosition());
		int direction = Integer.signum(position - node.getPosition());
		IVisualizableNode current = node;
		IVisualizableNode preceding = null;
		while (targetShift > 0 && current != null && (current == node || priorityComparator.compare(current, node) < 0)) {
			Integer slack;
			if (direction < 0) {
				slack = getLeftSideSlack(current);
			}
			else {
				slack = getRightSideSlack(current);
			}
			if (slack == null) {
				slack = targetShift;
			}
			
			int shift = Math.min(targetShift, slack);
			current.setPosition(current.getPosition() + direction*shift);
			targetShift -= shift;

			preceding = current;
			if (direction < 0) {
				current = getPreviousNode(current);
			}
			else {
				current = getNextNode(current);
			}
		}
		boolean endShifted = (current == null);
		
		current = preceding;
		while (current != node) {
			if (direction < 0) {
				current = getNextNode(current);
			}
			else {
				current = getPreviousNode(current);
			}
			
			Integer slack;
			if (direction < 0) {
				slack = getLeftSideSlack(current);
			}
			else {
				slack = getRightSideSlack(current);
			}
			
			current.setPosition(current.getPosition() + direction*slack);
		}

		if (endShifted && direction < 0) {
			correctLeftBorder();
		}
	}

	private void correctLeftBorder() {
		int leftEdge = nodes.get(0).getLeftExtreme();
		
		if (leftEdge < 0) {
			forceShift(-leftEdge);
			
			ILayer layer = getNextLayer();
			while (layer != null) {
				layer.forceShift(-leftEdge);
				layer = layer.getNextLayer();
			}
			layer = getPreviousLayer();
			while (layer != null) {
				layer.forceShift(-leftEdge);
				layer = layer.getPreviousLayer();
			}
		}
	}

	public void setNodeOnNextFreePosition(IVisualizableNode node) {
		Integer previousNodeRightSide = getPreviousNodeRightSide(node);
		Integer newPosition = calculateNextFreePosition(previousNodeRightSide, node);
		setNodePosition(node, newPosition);
	}

	public void setNodeOnNextFreePositionWithoutChecking(IVisualizableNode node) {
		Integer previousNodeRightSide = getPreviousNodeRightSide(node);
		Integer newPosition = calculateNextFreePosition(previousNodeRightSide, node);
		setNodePositionWithoutChecking(node, newPosition);
	}

	public int getOverlapFreePosition(IVisualizableNode node) {
		IVisualizableNode previousNode = getPreviousNode(node);
		if (previousNode == null) {
			return calculateNextFreePosition(null, node);
		}
		return calculateNextFreePosition(previousNode.getRightSide(), node);
	}
	
	public Integer getRightSideSlack(IVisualizableNode node) {
		Integer rightBound = getNextNodeLeftSide(node);
		if (rightBound == null) {
			return null;
		}
		return rightBound - node.getRightSide() - getGapDimension().width;
	}

	public Integer getNextNodeLeftSide(IVisualizableNode node) {
		IVisualizableNode nextNode = getNextNode(node);
		if (nextNode == null) {
			return null;
		}
		return nextNode.getLeftSide();
	}
	
	public Integer getLeftSideSlack(IVisualizableNode node) {
		Integer leftBound = getPreviousNodeRightSide(node);
		if (leftBound == null) {
			return null;
		}
		return node.getLeftSide() - leftBound - getGapDimension().width;
	}

	public Integer getPreviousNodeRightSide(IVisualizableNode node) {
		IVisualizableNode previousNode = getPreviousNode(node);
		if (previousNode == null) {
			return null;
		}
		return previousNode.getRightSide();
	}
}