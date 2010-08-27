package net.sf.anathema.graph.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.graph.nodes.ISimpleNode;
import net.sf.anathema.graph.util.IncidentMatrixUtilities;

public class ProperHierarchicalGraph implements IProperHierarchicalGraph {
  private final Map<Integer, List<ISimpleNode>> nodesByLayer = new HashMap<Integer, List<ISimpleNode>>();
  private final int deepestLayer;
  private final ISimpleNode[] allNodes;
  private final IGraphType type;

  public IGraphType getType() {
    return type;
  }

  public ProperHierarchicalGraph(final ISimpleNode[] hierarchicalGraph, final int deepestLayer) {
    this.allNodes = hierarchicalGraph;
    this.deepestLayer = deepestLayer;
    for (int index = 1; index <= deepestLayer; index++) {
      nodesByLayer.put(index, new ArrayList<ISimpleNode>());
    }
    for (ISimpleNode node : hierarchicalGraph) {
      for (ISimpleNode child : node.getChildren()) {
        Ensure.ensureArgumentEquals(node + " and " + child + " are more than one layer apart.", //$NON-NLS-1$ //$NON-NLS-2$
            1,
            child.getLayer() - node.getLayer());
      }
      nodesByLayer.get(node.getLayer()).add(node);
    }
    this.type = identifyGraphType();
  }

  private ProperHierarchicalGraph(final ISimpleNode[] nodes, final int deepestLayer, final IGraphType type) {
    this.allNodes = nodes;
    this.deepestLayer = deepestLayer;
    this.type = type;
    for (int index = 1; index - 1 < deepestLayer; index++) {
      nodesByLayer.put(index, new ArrayList<ISimpleNode>());
    }
    for (ISimpleNode node : nodes) {
      nodesByLayer.get(node.getLayer()).add(node);
    }
  }

  private GraphType identifyGraphType() {
    if (isInvertedTree()) {
      return GraphType.InvertedTree;
    }
    if (isTree()) {
      return GraphType.Tree;
    }
    return GraphType.DirectedGraph;
  }

  private boolean isTree() {
    if (getNodesByLayer(1).length != 1) {
      return false;
    }
    for (int layerIndex = 2; layerIndex <= deepestLayer; layerIndex++) {
      for (ISimpleNode node : getNodesByLayer(layerIndex)) {
        if (node.getParents().length != 1) {
          return false;
        }
      }
    }
    return true;
  }

  private boolean isInvertedTree() {
    if (getNodesByLayer(deepestLayer).length != 1) {
      return false;
    }
    for (int layerIndex = 1; layerIndex <= deepestLayer - 1; layerIndex++) {
      for (ISimpleNode node : getNodesByLayer(layerIndex)) {
        if (node.getChildren().length != 1) {
          return false;
        }
      }
    }
    return true;
  }

  public ISimpleNode[] getNodesByLayer(final int layer) {
    return nodesByLayer.get(layer).toArray(new ISimpleNode[0]);
  }

  public int getDeepestLayer() {
    return deepestLayer;
  }

  public void setNewLayerOrder(final int layer, final ISimpleNode[] orderedNodes) {
    List<ISimpleNode> layerNodes = nodesByLayer.get(layer);
    boolean equalSize = layerNodes.size() == orderedNodes.length;
    List<ISimpleNode> orderedNodeList = Arrays.asList(orderedNodes);
    boolean newNodes = !layerNodes.containsAll(orderedNodeList);
    if (!equalSize || newNodes) {
      throw new IllegalArgumentException("Layer content must not be changed " + Arrays.deepToString(orderedNodes)); //$NON-NLS-1$
    }
    nodesByLayer.put(layer, orderedNodeList);
    if (layer == 1) {
      return;
    }
    for (ISimpleNode node : nodesByLayer.get(layer - 1)) {
      node.reorderChildren(orderedNodes);
    }
  }

  public int calculateNumberOfCrossings(final int upperLayerIndex) {
    ISimpleNode[] upperLayer = getNodesByLayer(upperLayerIndex);
    ISimpleNode[] lowerLayer = getNodesByLayer(upperLayerIndex + 1);
    boolean[][] matrix = IncidentMatrixUtilities.buildMatrix(upperLayer, lowerLayer);
    int crossCount = 0;
    for (int outerUpperLayerIndex = 0; outerUpperLayerIndex + 1 < upperLayer.length; outerUpperLayerIndex++) {
      for (int innerUpperLayerIndex = outerUpperLayerIndex + 1; innerUpperLayerIndex < upperLayer.length; innerUpperLayerIndex++) {
        for (int outerLowerLayerIndex = 0; outerLowerLayerIndex + 1 < lowerLayer.length; outerLowerLayerIndex++) {
          for (int innerLowerLayerIndex = outerLowerLayerIndex + 1; innerLowerLayerIndex < lowerLayer.length; innerLowerLayerIndex++) {
            boolean crosses = matrix[outerUpperLayerIndex][innerLowerLayerIndex]
                && matrix[innerUpperLayerIndex][outerLowerLayerIndex];
            if (crosses) {
              crossCount++;
            }
          }
        }
      }
    }
    return crossCount;
  }

  public int calculateTotalNumberOfCrossings() {
    int crossCount = 0;
    for (int upperLayerIndex = 1; upperLayerIndex < getDeepestLayer(); upperLayerIndex++) {
      crossCount += calculateNumberOfCrossings(upperLayerIndex);
    }
    return crossCount;
  }

  @Override
  public ProperHierarchicalGraph clone() {
    return new ProperHierarchicalGraph(allNodes, deepestLayer, type);
  }

  public boolean containsRoot(final int layer) {
    for (ISimpleNode node : getNodesByLayer(layer)) {
      if (node.isRootNode()) {
        return true;
      }
    }
    return false;
  }
}