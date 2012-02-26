package net.sf.anathema.graph.ordering;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.graph.graph.IProperHierarchicalGraph;
import net.sf.anathema.graph.nodes.ISimpleNode;
import net.sf.anathema.graph.nodes.WeightedNode;
import net.sf.anathema.graph.nodes.WeightedNodeComparator;
import net.sf.anathema.graph.util.BarycenterCalculator;
import net.sf.anathema.graph.util.IncidentMatrixUtilities;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.lang.IntegerUtilities;

public abstract class AbstractVertexOrderer implements IVertexOrderer {

  protected final IProperHierarchicalGraph graph;
  private final Comparator<WeightedNode> comparator = new WeightedNodeComparator();

  public AbstractVertexOrderer(IProperHierarchicalGraph graph) {
    this.graph = graph;
  }

  protected final WeightedNode[] getNodesWeightedByIncomingEdges(ISimpleNode[] upperLayer, ISimpleNode[] lowerLayer) {
    boolean[][] matrix = IncidentMatrixUtilities.buildMatrix(upperLayer, lowerLayer);
    WeightedNode[] weightedNodes = new WeightedNode[lowerLayer.length];
    for (int columnIndex = 0; columnIndex < matrix[0].length; columnIndex++) {
      boolean[] columnVector = IncidentMatrixUtilities.getColumnVector(matrix, columnIndex);
      Double vectorCenter = BarycenterCalculator.calculateVectorCenter(columnVector);
      weightedNodes[columnIndex] = new WeightedNode(lowerLayer[columnIndex], vectorCenter);
    }
    return weightedNodes;
  }

  protected final WeightedNode[] getNodesWeightedByOutgoingEdges(ISimpleNode[] upperLayer, ISimpleNode[] lowerLayer) {
    boolean[][] matrix = IncidentMatrixUtilities.buildMatrix(upperLayer, lowerLayer);
    WeightedNode[] weightedNodes = new WeightedNode[upperLayer.length];
    for (int rowIndex = 0; rowIndex < matrix.length; rowIndex++) {
      boolean[] rowVector = matrix[rowIndex];
      Double vectorCenter = BarycenterCalculator.calculateVectorCenter(rowVector);
      weightedNodes[rowIndex] = new WeightedNode(upperLayer[rowIndex], vectorCenter);
    }
    return weightedNodes;
  }

  private void setLayerOrder(int layerIndex, WeightedNode[] weightedNodes) {
    ISimpleNode[] sortedLayer = new ISimpleNode[weightedNodes.length];
    for (int nodeIndex = 0; nodeIndex < weightedNodes.length; nodeIndex++) {
      sortedLayer[nodeIndex] = weightedNodes[nodeIndex].getNode();
    }
    graph.setNewLayerOrder(layerIndex, sortedLayer);
  }

  protected final void sortLayerByIncomingEdgeBarycenter(int layerIndex) {
    ISimpleNode[] upperLayer = graph.getNodesByLayer(layerIndex - 1);
    ISimpleNode[] lowerLayer = graph.getNodesByLayer(layerIndex);
    WeightedNode[] weightedLowerLayerNodes = getNodesWeightedByIncomingEdges(upperLayer, lowerLayer);
    Arrays.sort(weightedLowerLayerNodes, comparator);
    setLayerOrder(layerIndex, weightedLowerLayerNodes);
  }

  protected final void sortLayerByOutgoingEdgeBarycenter(int layerIndex) {
    ISimpleNode[] upperLayer = graph.getNodesByLayer(layerIndex);
    ISimpleNode[] lowerLayer = graph.getNodesByLayer(layerIndex + 1);
    WeightedNode[] weightedUpperLayerNodes = getNodesWeightedByOutgoingEdges(upperLayer, lowerLayer);
    Arrays.sort(weightedUpperLayerNodes, comparator);
    setLayerOrder(layerIndex, weightedUpperLayerNodes);
  }

  protected final boolean hasRemainingCrossings() {
    return graph.calculateTotalNumberOfCrossings() != 0;
  }

  protected final boolean isCrossingFree() {
    return !hasRemainingCrossings();
  }

  protected final void permutateEqualWeightedNodex(WeightedNode[] weightedLayerNodes) {
    MultiEntryMap<Double, Integer> nodeIndicesByWeight = getWeightSeparation(weightedLayerNodes);
    for (Double weight : nodeIndicesByWeight.keySet()) {
      List<Integer> indexList = nodeIndicesByWeight.get(weight);
      int[] indices = new int[indexList.size()];
      for (int index = 0; index < indices.length; index++) {
        indices[index] = indexList.get(index);
      }
      permutateNodes(weightedLayerNodes, indices);
    }
  }

  protected MultiEntryMap<Double, Integer> getWeightSeparation(WeightedNode[] weightedLayerNodes) {
    MultiEntryMap<Double, Integer> nodeIndicesByWeight = new MultiEntryMap<Double, Integer>();
    for (int index = 0; index < weightedLayerNodes.length; index++) {
      Double weight = weightedLayerNodes[index].getWeight();
      if (weight != null) {
        nodeIndicesByWeight.add(weight, index);
      }
      else {
        nodeIndicesByWeight.add(Double.NaN, index);
      }
    }
    return nodeIndicesByWeight;
  }

  private void permutateNodes(WeightedNode[] weightedLayerNodes, int[] originalIndices) {
    if (originalIndices.length <= 1) {
      return;
    }
    int[] indexArray = new int[originalIndices.length];
    Map<Integer, WeightedNode> nodesByOriginalIndex = new HashMap<Integer, WeightedNode>();
    for (int indexIndex = 0; indexIndex < originalIndices.length; indexIndex++) {
      int originalIndex = originalIndices[indexIndex];
      indexArray[indexIndex] = originalIndex;
      nodesByOriginalIndex.put(originalIndex, weightedLayerNodes[originalIndex]);
    }
    indexArray = IntegerUtilities.permutate(indexArray);
    for (int indexIndex = 0; indexIndex < originalIndices.length; indexIndex++) {
      int originalIndex = originalIndices[indexIndex];
      int permutatedIndex = indexArray[indexIndex];
      weightedLayerNodes[permutatedIndex] = nodesByOriginalIndex.get(originalIndex);
    }
  }
}