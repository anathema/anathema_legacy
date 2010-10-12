package net.sf.anathema.platform.svgtree.document.components;

import java.util.Comparator;

public final class PriorityNodeComparator implements Comparator<IVisualizableNode> {
	public static final int LONG_EDGE_PRIORITY = 8;
	public static final int LONG_END_PRIORITY = 2;
	public static final int PROPER_EDGE_PRIORITY = 1;
	
	Direction direction;
	public PriorityNodeComparator(Direction direction) {
		this.direction = direction;
	}

	public int compare(IVisualizableNode node1, IVisualizableNode node2) {
		if (direction == Direction.DOWN) {
			return priority(node1, node1.getParents()) - priority(node2, node2.getParents());
		}
		else {
			return priority(node1, node1.getChildren()) - priority(node2, node2.getChildren());
		}
	}

	private int priority(IVisualizableNode node, IVisualizableNode[] neighbors) {
		int priority = 0;
		if (node instanceof VisualizableDummyNode) {
			for (IVisualizableNode neighbor : neighbors) {
				if (neighbor instanceof VisualizableDummyNode) {
					priority += LONG_EDGE_PRIORITY;
				}
				else {
					priority += LONG_END_PRIORITY;
				}
			}
		}
		else {
			for (IVisualizableNode neighbor : neighbors) {
				if (neighbor instanceof VisualizableDummyNode) {
					priority += LONG_END_PRIORITY; 
				}
				else {
					priority += PROPER_EDGE_PRIORITY;
				}
			}
		}
		return priority;
	}
}
