package net.sf.anathema.graph.graph;

import com.google.common.base.Preconditions;
import net.sf.anathema.graph.nodes.ISimpleNode;
import net.sf.anathema.graph.util.IncidentMatrixUtilities;
import net.sf.anathema.lib.exception.UnreachableCodeReachedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProperHierarchicalGraph implements IProperHierarchicalGraph, Cloneable {
  // This is volatile instead of final to allow clone to be implemented
  private volatile Map<Integer, List<ISimpleNode>> nodesByLayer = new HashMap<>();
  private final int deepestLayer;
  private final ISimpleNode[] allNodes;
  private final IGraphType type;

  @Override
  public IGraphType getType() {
    return type;
  }

  public ProperHierarchicalGraph(ISimpleNode[] hierarchicalGraph, int deepestLayer) {
    this.allNodes = hierarchicalGraph;
    this.deepestLayer = deepestLayer;
    for (int index = 1; index <= deepestLayer; index++) {
      nodesByLayer.put(index, new ArrayList<ISimpleNode>());
    }
    for (ISimpleNode node : hierarchicalGraph) {
      for (ISimpleNode child : node.getChildren()) {
        String message = node + " and " + child + " are more than one layer apart.";
        Preconditions.checkArgument(child.getLayer() - node.getLayer() == 1, message);
      }
      nodesByLayer.get(node.getLayer()).add(node);
    }
    this.type = identifyGraphType();
  }

  private ProperHierarchicalGraph(ISimpleNode[] nodes, int deepestLayer, IGraphType type) {
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

  @Override
  public ISimpleNode[] getNodesByLayer(int layer) {
    List<ISimpleNode> nodeList = nodesByLayer.get(layer);
    return nodeList.toArray(new ISimpleNode[nodeList.size()]);
  }

  @Override
  public int getDeepestLayer() {
    return deepestLayer;
  }

  @Override
  public void setNewLayerOrder(int layer, ISimpleNode[] orderedNodes) {
    List<ISimpleNode> layerNodes = nodesByLayer.get(layer);
    boolean equalSize = layerNodes.size() == orderedNodes.length;
    List<ISimpleNode> orderedNodeList = Arrays.asList(orderedNodes);
    boolean newNodes = !layerNodes.containsAll(orderedNodeList);
    if (!equalSize || newNodes) {
      throw new IllegalArgumentException("Layer content must not be changed " + Arrays.deepToString(orderedNodes));
    }
    nodesByLayer.put(layer, orderedNodeList);
    if (layer == 1) {
      return;
    }
    for (ISimpleNode node : nodesByLayer.get(layer - 1)) {
      node.reorderChildren(orderedNodes);
    }
  }

  @Override
  public int calculateNumberOfCrossings(int upperLayerIndex) {
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

  @Override
  public int calculateTotalNumberOfCrossings() {
    int crossCount = 0;
    for (int upperLayerIndex = 1; upperLayerIndex < getDeepestLayer(); upperLayerIndex++) {
      crossCount += calculateNumberOfCrossings(upperLayerIndex);
    }
    return crossCount;
  }

  @SuppressWarnings("CloneDoesntDeclareCloneNotSupportedException")
  @Override
  public ProperHierarchicalGraph clone() {
    ProperHierarchicalGraph clone;
    try {
      clone = (ProperHierarchicalGraph)super.clone();
    } catch (CloneNotSupportedException e) {
      throw new UnreachableCodeReachedException(e);
    }
    ProperHierarchicalGraph tmpCopy = new ProperHierarchicalGraph(allNodes, deepestLayer, type);
    clone.nodesByLayer = tmpCopy.nodesByLayer;
    return clone;
  }

  @Override
  public boolean containsRoot(int layer) {
    for (ISimpleNode node : getNodesByLayer(layer)) {
      if (node.isRootNode()) {
        return true;
      }
    }
    return false;
  }
}