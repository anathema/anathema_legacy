package net.sf.anathema.graph.layering;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import net.sf.anathema.graph.nodes.IRegularNode;
import net.sf.anathema.graph.nodes.ISimpleNode;
import net.sf.anathema.lib.collection.MultiEntryMap;

public class GansnerLayerer implements ILayerer {

	private static class GansnerEdge implements Comparable<GansnerEdge> {
		// Note: this class has a natural ordering that is inconsistent with equals
		
		IRegularNode head;
		IRegularNode tail;
		
		int delta;
		int weight;
		
		Integer cutValue;
		
		public IRegularNode getHead() {
			return head;
		}
		
		public IRegularNode getTail() {
			return tail;
		}
		
		/*public int getDelta() {
			return delta;
		}
		public void setDelta(int delta) {
			this.delta = delta;
		}*/
		
		public int getWeight() {
			return weight;
		}
		/*public void setWeight(int weight) {
			this.weight = weight;
		}*/
		
		public Integer getCutValue() {
			return cutValue;
		}
		public void setCutValue(Integer cutValue) {
			this.cutValue = cutValue;
		}
		
		public int getLength() {
			return tail.getLayer() - head.getLayer();
		}
		
		public int getSlack() {
			return getLength() - delta;
		}

		@Override
		public int compareTo(GansnerEdge o) {
			// TODO Auto-generated method stub
			return this.getSlack() - o.getSlack();
		}

		public boolean equals(Object obj) {
		  // Notice that equals is inconsistent with compareTo
		  if (obj == null || obj.getClass() != getClass()) {
		    return false;
		  }

		  GansnerEdge other = (GansnerEdge)obj;
		  return head.equals(other.head) && tail.equals(other.tail);
		}
		
		public int hashCode() {
			return head.hashCode() ^ tail.hashCode();
		}
		/*public boolean equals(GansnerEdge edge) {
			return (this.head == edge.getHead()) && (this.tail == edge.getTail());
		}*/

		public GansnerEdge(IRegularNode head, IRegularNode tail) {
			this(head, tail, 1, 1, null);
		}
		/*public GansnerEdge(IRegularNode head, IRegularNode tail, int delta) {
			this(head, tail, delta, 1, null);
		}
		public GansnerEdge(IRegularNode head, IRegularNode tail, int delta, int weight) {
			this(head, tail, delta, weight, null);
		}*/
		public GansnerEdge(IRegularNode head, IRegularNode tail, int delta, int weight, Integer cutValue) {
			this.head = head;
			this.tail = tail;
			this.delta = delta;
			this.weight = weight;
			this.cutValue = cutValue;
		}
	}

	public int layerGraph(IRegularNode[] acyclicGraph) {
		IRegularNode[] topologicallySortedNodes = TopologyBuilder.sortGraphByTopology(acyclicGraph);
		setLayersToOne(topologicallySortedNodes);
		initializeRanking(topologicallySortedNodes);
		
		return optimizeRanking(topologicallySortedNodes);
	}

	private int initializeRanking(IRegularNode[] topologicallySortedNodes) {
		int deepestLayer = 1;
		for (IRegularNode node : topologicallySortedNodes) {
			deepestLayer = setChildLayers(deepestLayer, node);
		}
		return deepestLayer;
	}

	private int setChildLayers(int deepestLayer, IRegularNode node) {
		for (ISimpleNode child : node.getChildren()) {
			IRegularNode regularChild = (IRegularNode) child;
			regularChild.setLayer(Math.max(regularChild.getLayer(), node.getLayer() + 1));
			deepestLayer = Math.max(regularChild.getLayer(), deepestLayer);
		}
		return deepestLayer;
	}
	
	private int optimizeRanking(IRegularNode[] nodes) {
		// TODO: Separate graph into connected components
		
		HashSet<GansnerEdge> graph = new HashSet<GansnerEdge>();
		MultiEntryMap<IRegularNode, GansnerEdge> incident = new MultiEntryMap<IRegularNode, GansnerEdge>();
		for (IRegularNode node : nodes) {
			for (ISimpleNode child : node.getChildren()) {
				IRegularNode regularChild = (IRegularNode) child;
				
				GansnerEdge edge = new GansnerEdge(node, regularChild);
				graph.add(edge);
				incident.add(edge.getHead(), edge);
				incident.add(edge.getTail(), edge);
			}
		}
		
		feasibleTree(graph, incident);
		GansnerEdge cutEdge = findCutEdge();
		while (cutEdge != null) {
			exchangeEdge(graph, incident, cutEdge);
			
			cutEdge = findCutEdge();
		}
		
		int deepestLayer = normalizeRanking(nodes);
		balanceRanking(nodes, incident, deepestLayer);

		return deepestLayer;
	}
	
	private void balanceRanking(IRegularNode[] nodes, MultiEntryMap<IRegularNode, GansnerEdge> incident, int deepestLayer) {
		int[] nodesPerLayer = new int[deepestLayer + 1];
		for (int i = 0; i <= deepestLayer; i++) {
			nodesPerLayer[i] = 0;
		}
		for (IRegularNode node : nodes) {
			nodesPerLayer[node.getLayer()]++;
		}
		
		for (IRegularNode node : nodes) {
			int inWeight = 0;
			int outWeight = 0;
			
			int upSlack = Integer.MAX_VALUE;
			int downSlack = Integer.MAX_VALUE;
			
			for (GansnerEdge edge : incident.get(node)) {
				if (edge.getHead() == node) {
					outWeight += edge.getWeight();
					downSlack = Math.min(downSlack, edge.getSlack());
				}
				else {
					inWeight += edge.getWeight();
					upSlack = Math.min(upSlack, edge.getSlack());
				}
			}
			
			if (inWeight == outWeight) {
				int bestLayer = node.getLayer();
				int bestCount = nodesPerLayer[bestLayer] - 1;
				for (int layer = node.getLayer() - upSlack; layer <= node.getLayer() + downSlack; layer++) {
					if (nodesPerLayer[layer] < bestCount) {
						bestLayer = layer;
						bestCount = nodesPerLayer[bestLayer];
					}
				}
				nodesPerLayer[node.getLayer()]--;
				node.setLayer(bestLayer);
				nodesPerLayer[bestLayer]++;
			}
		}
	}
	
	private int normalizeRanking(IRegularNode[] nodes) {
		int minLayer = Integer.MAX_VALUE;
		for (IRegularNode node : nodes) {
			minLayer = Math.min(minLayer, node.getLayer());
		}
		
		int deepestLayer = 0;
		for (IRegularNode node : nodes) {
			node.setLayer(node.getLayer() - minLayer);
			deepestLayer = Math.max(deepestLayer, node.getLayer());
		}
		return deepestLayer;
	}
	
	private GansnerEdge findCutEdge() {
		for (GansnerEdge edge : tree) {
			if (edge.getCutValue() < 0) {
				return edge;
			}
		}
		return null;
	}
	
	private GansnerEdge exchangeEdge(Set<GansnerEdge> graph,
			                         MultiEntryMap<IRegularNode, GansnerEdge> incident,
			                         GansnerEdge cutEdge) {
		Set<IRegularNode> toBeDetermined = new HashSet<IRegularNode>(treeNodes);
		Set<IRegularNode> checked = new HashSet<IRegularNode>();
		
		Set<IRegularNode> headComponent = new HashSet<IRegularNode>();
		headComponent.add(cutEdge.getHead());
		toBeDetermined.remove(cutEdge.getHead());
		
		Set<IRegularNode> tailComponent = new HashSet<IRegularNode>();
		tailComponent.add(cutEdge.getTail());
		toBeDetermined.remove(cutEdge.getTail());
		
		while (!toBeDetermined.isEmpty()) {
			for (IRegularNode node : headComponent) {
				if (!checked.contains(node)) {
					for (GansnerEdge edge : treeIncident.get(node)) {
						headComponent.add(edge.getHead());
						headComponent.add(edge.getTail());
						toBeDetermined.remove(edge.getHead());
						toBeDetermined.remove(edge.getTail());
					}
					checked.add(node);
				}
			}
			for (IRegularNode node : tailComponent) {
				if (!checked.contains(node)) {
					for (GansnerEdge edge : treeIncident.get(node)) {
						tailComponent.add(edge.getHead());
						tailComponent.add(edge.getTail());
						toBeDetermined.remove(edge.getHead());
						toBeDetermined.remove(edge.getTail());
					}
					checked.add(node);
				}
			}
		}

		GansnerEdge bestEdge = null;
		int minSlack = Integer.MAX_VALUE;
		
		for (GansnerEdge edge : graph) {
			if (edge.getSlack() < minSlack
					&& ((headComponent.contains(edge.getHead()) && tailComponent.contains(edge.getTail()))
							|| (headComponent.contains(edge.getTail())) && tailComponent.contains(edge.getHead()))) {
				bestEdge = edge;
				minSlack = edge.getSlack();
			}
		}
		
		tree.remove(cutEdge);
		treeIncident.removeValue(cutEdge.getHead(), cutEdge);
		treeIncident.removeValue(cutEdge.getTail(), cutEdge);
		tree.add(bestEdge);
		treeIncident.add(bestEdge.getHead(), bestEdge);
		treeIncident.add(bestEdge.getTail(), bestEdge);
		
		resetCutValues(graph, incident);
		
		return bestEdge;
	}
	
	Set<GansnerEdge> tree;
	Set<IRegularNode> treeNodes;
	MultiEntryMap<IRegularNode, GansnerEdge> treeIncident;
	private Set<GansnerEdge> feasibleTree(Set<GansnerEdge> graph,
			                              MultiEntryMap<IRegularNode, GansnerEdge> incident) {
		int targetSize = incident.keySet().size() - 1;
		IRegularNode seedNode = incident.keySet().iterator().next();
		
		GansnerEdge[] sortedEdges = graph.toArray(new GansnerEdge[0]);
		Arrays.sort(sortedEdges);
		
		buildTightTree(seedNode, graph, incident);
		while (tree.size() < targetSize) {
			for (GansnerEdge edge : sortedEdges) {
				if (treeNodes.contains(edge.getHead()) ^ treeNodes.contains(edge.getTail())) {
					int treeShift;
					if (treeNodes.contains(edge.getHead())) {
						treeShift = -edge.getSlack();
					}
					else {
						treeShift = edge.getSlack();
					}
					
					for (IRegularNode node : treeNodes) {
						node.setLayer(node.getLayer() + treeShift);
					}
					Arrays.sort(sortedEdges);
					break;
				}
			}
			
			buildTightTree(seedNode, graph, incident);
		}
		
		resetCutValues(graph, incident);
		
		return tree;
	}
	
	private void resetCutValues(Set<GansnerEdge> graph, MultiEntryMap<IRegularNode, GansnerEdge> incident) {
		for (GansnerEdge edge : graph) {
			edge.setCutValue(null);
		}
		fixCutValues(incident);
	}
	private void fixCutValues(MultiEntryMap<IRegularNode, GansnerEdge> incident) {
		Set<IRegularNode> unfixedEdges = new HashSet<IRegularNode>(treeNodes);
		while (!unfixedEdges.isEmpty()) {
			for (IRegularNode node : unfixedEdges) {
				int unfixedCount = 0;
				for (GansnerEdge edge : treeIncident.get(node)) {
					if (edge.getCutValue() == null) {
						unfixedCount++;
					}
				}
				if (unfixedCount == 1) {
					for (GansnerEdge edgeToFix : treeIncident.get(node)) {
						if (edgeToFix.getCutValue() == null) {
							int cutValue = edgeToFix.getWeight();
							for (GansnerEdge edge : incident.get(node)) {
								if (edge == edgeToFix) continue;
								
								if (tree.contains(edge)) {
									if (edge.getHead() == node) {
										cutValue -= edge.getCutValue() - edge.getWeight();
									}
									else {
										cutValue += edge.getCutValue() - edge.getWeight();
									}
								}
								else {
									if (edge.getHead() == node) {
										cutValue -= edge.getWeight();
									}
									else {
										cutValue += edge.getWeight();
									}
								}
							}
							edgeToFix.setCutValue(cutValue);
							break;
						}
					}
					unfixedEdges.remove(node);
					break;
				}
			}
		}
	}
	
	private int buildTightTree(IRegularNode seedNode,
			                                Set<GansnerEdge> graph,
			                                MultiEntryMap<IRegularNode, GansnerEdge> incident) {
		tree = new HashSet<GansnerEdge>();
		treeNodes = new HashSet<IRegularNode>();
		treeIncident = new MultiEntryMap<IRegularNode, GansnerEdge>();
		
		int maxDegree = 0;
		Set<IRegularNode> newNodes = new HashSet<IRegularNode>();
		newNodes.add(seedNode);
		while (!newNodes.isEmpty()) {
			treeNodes.addAll(newNodes);
			newNodes.clear();
			 
			for (IRegularNode node : treeNodes) {
				for (GansnerEdge edge : incident.get(node)) {
					if ((edge.getSlack() == 0)
							&& ((!treeNodes.contains(edge.getHead()) && !newNodes.contains(edge.getHead()))
									|| (!treeNodes.contains(edge.getTail()) && !newNodes.contains(edge.getTail())))) {
						IRegularNode head = edge.getHead();
						newNodes.add(head);
						treeIncident.add(head, edge);
						maxDegree = Math.max(maxDegree, treeIncident.get(head).size());
						
						IRegularNode tail = edge.getTail();
						newNodes.add(tail);
						treeIncident.add(tail, edge);
						maxDegree = Math.max(maxDegree, treeIncident.get(tail).size());
						
						tree.add(edge);
					}
				}
			}
		}
		
		return maxDegree;
	}

	private void setLayersToOne(IRegularNode[] topologicallySortedNodes) {
		for (IRegularNode node : topologicallySortedNodes) {
			node.setLayer(1);
		}
	}
}