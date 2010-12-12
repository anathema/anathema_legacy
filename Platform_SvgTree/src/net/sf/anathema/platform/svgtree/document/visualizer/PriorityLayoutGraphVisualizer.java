package net.sf.anathema.platform.svgtree.document.visualizer;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import net.sf.anathema.graph.graph.IProperHierarchicalGraph;
import net.sf.anathema.graph.nodes.ISimpleNode;
import net.sf.anathema.lib.collection.ListOrderedSet;
import net.sf.anathema.platform.svgtree.document.components.Direction;
import net.sf.anathema.platform.svgtree.document.components.ILayer;
import net.sf.anathema.platform.svgtree.document.components.IVisualizableNode;
import net.sf.anathema.platform.svgtree.document.components.PriorityLayer;
import net.sf.anathema.platform.svgtree.document.util.BackwardsIterable;

public class PriorityLayoutGraphVisualizer extends AbstractCascadeVisualizer {

  // BASIC:
  // 1. Build Sugiyama layout
  // 1b. Create Metanodes for nodes who have only common children.
  // (Location conflicts arising from proximity (not identical location) should be resolved by the layer, not
  // the nodes.)
  // 2. Insert all nodes into their appropriate layers, as far to the left as possible.
  // 3. Reposition the nodes iteratively, according to Sugiyama's priority layout method
  // 4. Break up Metanodes
  // 5. Draw Nodes

  // MULTIPLE PARENTS:
  // If a node has multiple parents, they should be placed next to each other
  // (This is implied by the crossing-reduction constraints and should happen automatically)
  // The parents are combined into a single "meta node", which connects to all of the
  // included node's parents and all of their children.
  // During calculation, meta nodes are treated as a single large node.
  // Their position is calculated as usual, with free space for placement
  // of the individual nodes surrounding them.
  // Once calculation is done, the nodes placed symmetrically around the calculated center location in
  // the order determined by the Sugiyama layout.

  // DRAWING:
  // Once node placement is complete, node drawing occurs.
  // First all nodes are drawn, with empty space left for the dummy nodes.
  // Then, edges are created.
  // Edges between two regular nodes are drawn as ususal.
  // Edges between a regular node and a dummy node are drawn as a polyline.
  // Since dummy nodes always have a single child, it can be assumed that the edge from dummy node
  // to it's child is as straight as possible. Thus:
  // From the parent to the dummy node, a line is drawn.
  // These lines should always attach to the top-center spot of the dummy nodes.
  // Within the dummy node, the line is extended straight downwards, arriving at the bottom-center spot.
  // The line is then drawn to the next child node, either a regular node (edge may arrive anywhere
  // and needs to end in an arrow) or a dummy node (repeat above steps).

  // CHILDGROUPS:
  // For each leaf, calculate all of it's ancestors.
  // If two nodes on the same layer are reachable by traversing the tree from the same leaf,
  // they form a childgroup (often they will also be part of a horizontal metanode) and may be placed as close
  // to each other as possible without overlapping their edges
  // Instead of giving the entire map as argument, just give the relevant leaves?

  private final int ITERATIONS = 5;

  public PriorityLayoutGraphVisualizer(IProperHierarchicalGraph graph, ITreePresentationProperties properties) {
    super(properties, graph);
  }

  public IVisualizedGraph buildTree() {
    int layerCount = getGraph().getDeepestLayer();
    
    for (int layerIndex = layerCount - 1; layerIndex >= 0; layerIndex--) {
      createVisualizableNodes(layerIndex);
    }
    PriorityLayer[] layers = createLayers(layerCount);
    
    for (PriorityLayer layer : new BackwardsIterable<PriorityLayer>(layers)) {
      layer.positionNodes();
    }
    
    // As Sugiyama recommends, we adjust our vertices in a down-up-down alternating pattern
    priorityLayoutSweep(layers, Direction.DOWN);
    for (int i = 0; i < ITERATIONS; i++) {
      priorityLayoutSweep(layers, Direction.UP);
      priorityLayoutSweep(layers, Direction.DOWN);
    }
    
    for (PriorityLayer layer : new BackwardsIterable<PriorityLayer>(layers)) {
      layer.unrollHorizontalMetanodes();
    }

    return new VisualizedGraph(createXml(layers), getTreeDimension(layers));
  }
  
  private void priorityLayoutSweep(PriorityLayer[] layers, Direction direction) {
    Iterable<PriorityLayer> layerSweep;
    if (direction == Direction.UP) {
      layerSweep = new BackwardsIterable<PriorityLayer>(layers);
    }
    else {
      layerSweep = Arrays.asList(layers);
    }
    
    for (PriorityLayer layer : layerSweep) {
      layer.repositionNodes(direction);
    }
  }

  private void createVisualizableNodes(int layerIndex) {
    ISimpleNode[] layerNodes = getGraph().getNodesByLayer(layerIndex + 1);
    for (int nodeIndex = 0; nodeIndex < layerNodes.length; nodeIndex++) {
      ISimpleNode currentNode = layerNodes[nodeIndex];
      if (isVisualizableNodeRegistered(currentNode)) {
        continue;
      }
      boolean horizontalMetanodeFound = lookForHorizontalMetaNodes(layerNodes, nodeIndex, currentNode);
      if (horizontalMetanodeFound) {
        continue;
      }
      if (!isVisualizableNodeRegistered(currentNode)) {
        getNodeFactory().registerVisualizableNode(currentNode);
      }
    }
  }

  private boolean lookForHorizontalMetaNodes(ISimpleNode[] layerNodes, int nodeIndex, ISimpleNode currentNode) {
    Set<ISimpleNode> identicalChildrenNodes = new ListOrderedSet<ISimpleNode>();
    identicalChildrenNodes.add(currentNode);
    for (int compareNodeIndex = nodeIndex + 1; compareNodeIndex < layerNodes.length; compareNodeIndex++) {
      ISimpleNode compareNode = layerNodes[compareNodeIndex];
      if (haveIdenticalChildren(currentNode, compareNode)) {
        for (int betweenNodeIndex = nodeIndex + 1; betweenNodeIndex < compareNodeIndex; betweenNodeIndex++) {
          identicalChildrenNodes.add(layerNodes[betweenNodeIndex]);
        }
        identicalChildrenNodes.add(compareNode);
      }
    }
    if (identicalChildrenNodes.size() > 1) {
      getNodeFactory().registerHorizontalMetaNode(identicalChildrenNodes);
      return true;
    }
    return false;
  }

  private boolean haveIdenticalChildren(ISimpleNode node1, ISimpleNode node2) {
    if (node1.isLeafNode() || node2.isLeafNode()) {
      return false;
    }
    List<IVisualizableNode> firstVisualizableChildren = new ArrayList<IVisualizableNode>();
    for (ISimpleNode node : node1.getChildren()) {
      firstVisualizableChildren.add(getVisualizableNode(node));
    }
    List<IVisualizableNode> secondVisualizableChildren = new ArrayList<IVisualizableNode>();
    for (ISimpleNode node : node2.getChildren()) {
      secondVisualizableChildren.add(getVisualizableNode(node));
    }
    return firstVisualizableChildren.containsAll(secondVisualizableChildren)
        && firstVisualizableChildren.size() == secondVisualizableChildren.size();
  }

  protected Dimension getTreeDimension(ILayer[] layers) {
    return new Dimension(getTreeWidth(layers), getTreeHeight(layers));
  }

  protected int getTreeWidth(ILayer[] layers) {
    int width = 0;
    for (ILayer layer : layers) {
      width = Math.max(width, layer.getWidth());
    }
    return width;
  }

  protected PriorityLayer[] createLayers(int layerCount) {
    PriorityLayer[] layers = new PriorityLayer[layerCount];
    initLayers(layers);
    for (int layerIndex = layerCount - 1; layerIndex >= 0; layerIndex--) {
      addNodesToLayers(layerIndex, layers);
    }
    return layers;
  }

  private void addNodesToLayers(int layerIndex, PriorityLayer[] layers) {
    ILayer currentLayer = layers[layerIndex];
    ISimpleNode[] nodesOnLayer = getGraph().getNodesByLayer(layerIndex + 1);
    for (ISimpleNode node : nodesOnLayer) {
      currentLayer.addNode(getVisualizableNode(node));
    }
  }

  private void initLayers(PriorityLayer[] layers) {
    for (int layerIndex = 0; layerIndex < layers.length; layerIndex++) {
      int yPosition = layerIndex * (getProperties().getNodeDimension().height
                                  + getProperties().getGapDimension().height);
      layers[layerIndex] = new PriorityLayer(getProperties().getGapDimension(), yPosition);
    }
    for (int layerIndex = 1; layerIndex < layers.length; layerIndex++) {
      layers[layerIndex].setPreviousLayer(layers[layerIndex - 1]);
    }
    for (int layerIndex = 0; layerIndex < layers.length - 1; layerIndex++) {
      layers[layerIndex].setFollowUp(layers[layerIndex + 1]);
    }
  }
}