package net.sf.anathema.graph.layering;

import java.util.List;
import java.util.ArrayList;

import java.util.Random;

import net.sf.anathema.graph.nodes.IRegularNode;
import net.sf.anathema.graph.nodes.ISimpleNode;

public class LayerOverflowOptimizer {
	private static final int LAYER_WIDTH = 7;
	private static final Random RANDOM = new Random(1286689190);

	public static int splitLayer(List<IRegularNode> nodes, int deepestLayer) { 
		if (nodes.size() > LAYER_WIDTH) {
			int layer = nodes.get(0).getLayer();
			
			List<IRegularNode> layer1 = new ArrayList<IRegularNode>();
			List<IRegularNode> layer2 = new ArrayList<IRegularNode>();
			for (IRegularNode node : nodes) {
				if (node.isLeafNode()) {
					layer1.add(node);
				}
				else {
					layer2.add(node);
				}
			}
			while (layer2.size() > layer1.size()) {
				int index = RANDOM.nextInt(layer2.size());
				layer1.add(layer2.get(index));
				layer2.remove(index);
			}
			while (layer1.size() > layer2.size() + 1) {
				int index = RANDOM.nextInt(layer1.size());
				layer2.add(layer1.get(index));
				layer1.remove(index);
			}
			
			for (IRegularNode node : layer2) {
				node.setLayer(layer + 1);
			}
			for (IRegularNode node : nodes) {
				deepestLayer = fixChildren(node, layer + 2, deepestLayer);
			}
		}
		return deepestLayer;
	}
	
	private static int fixChildren(IRegularNode node, int minLayer, int deepestLayer) {
		for (ISimpleNode child : node.getChildren()) {
			IRegularNode regularChild = (IRegularNode) child;
			if (regularChild.getLayer() < minLayer) {
				regularChild.setLayer(minLayer);
				deepestLayer = Math.max(minLayer, deepestLayer);
				deepestLayer = fixChildren(regularChild, minLayer + 1, deepestLayer);
			}
		}
		return deepestLayer;
	}
}
