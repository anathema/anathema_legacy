package net.sf.anathema.graph.ordering;

import net.sf.anathema.graph.graph.IProperHierarchicalGraph;
import net.sf.anathema.graph.nodes.ISimpleNode;
import net.sf.anathema.graph.nodes.WeightedNode;
import net.sf.anathema.graph.util.BarycenterCalculator;
import net.sf.anathema.graph.util.IncidentMatrixUtilities;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.lang.IntegerUtilities;

public class SandraVertexOrderer extends AbstractVertexOrderer {

  public SandraVertexOrderer(IProperHierarchicalGraph graph) {
    super(graph);
  }

  public void processMultiLayerGraph() {
    int deepestLayer = graph.getDeepestLayer();
    for (int count = 0; count < 100; count++) {
      if (graph.calculateTotalNumberOfCrossings() == 0) {
        break;
      }
      doDownPhase(deepestLayer);
      doUpPhase(deepestLayer);
    }
  }

  private void doUpPhase(int deepestLayer) {
    for (int upperLayerIndex = deepestLayer - 1; upperLayerIndex >= 1; upperLayerIndex--) {
      if (graph.calculateTotalNumberOfCrossings() != 0) {
        sortLayerByOutgoingEdgeBarycenter(upperLayerIndex);
      }
      if (graph.calculateTotalNumberOfCrossings() != 0) {
        reorderUpperLayer(upperLayerIndex);
      }
    }
  }

  private void doDownPhase(int deepestLayer) {
    for (int upperLayerIndex = 1; upperLayerIndex < deepestLayer; upperLayerIndex++) {
      if (graph.calculateTotalNumberOfCrossings() != 0) {
        sortLayerByIncomingEdgeBarycenter(upperLayerIndex + 1);
      }
      if (graph.calculateTotalNumberOfCrossings() != 0) {
        reorderLowerLayer(upperLayerIndex);
      }
    }
  }

  private void reorderLowerLayer(int upperLayerIndex) {
    ISimpleNode[] upperLayer = graph.getNodesByLayer(upperLayerIndex);
    ISimpleNode[] lowerLayer = graph.getNodesByLayer(upperLayerIndex + 1);
    WeightedNode[] weightedLowerLayerNodes = getWeightedLowerLayerNodes(upperLayer, lowerLayer);
    MultiEntryMap<Double, Integer> weightSeparation = getWeightSeparation(weightedLowerLayerNodes);
    for (Double key : weightSeparation.keySet()) {
      int[] indices = IntegerUtilities.toIntArray(weightSeparation.get(key));
      for (int index = 0; index < indices.length - 1; index++) {
        exchangeNodes(weightedLowerLayerNodes, indices[index], indices[index + 1]);
        reorderLayer(upperLayerIndex + 1, weightedLowerLayerNodes);
        sortLayerByOutgoingEdgeBarycenter(upperLayerIndex);
        sortLayerByIncomingEdgeBarycenter(upperLayerIndex + 1);
        if (graph.calculateTotalNumberOfCrossings() == 0) {
          return;
        }
      }
    }
  }

  private void reorderUpperLayer(int upperLayerIndex) {
    ISimpleNode[] upperLayer = graph.getNodesByLayer(upperLayerIndex);
    ISimpleNode[] lowerLayer = graph.getNodesByLayer(upperLayerIndex + 1);
    WeightedNode[] weightedUpperLayerNodes = getWeightedUpperLayerNodes(upperLayer, lowerLayer);
    MultiEntryMap<Double, Integer> weightSeparation = getWeightSeparation(weightedUpperLayerNodes);
    for (Double key : weightSeparation.keySet()) {
      int[] indices = IntegerUtilities.toIntArray(weightSeparation.get(key));
      for (int index = 0; index < indices.length - 1; index++) {
        exchangeNodes(weightedUpperLayerNodes, indices[index], indices[index + 1]);
        reorderLayer(upperLayerIndex, weightedUpperLayerNodes);
        sortLayerByIncomingEdgeBarycenter(upperLayerIndex + 1);
        sortLayerByOutgoingEdgeBarycenter(upperLayerIndex);
        if (graph.calculateTotalNumberOfCrossings() == 0) {
          return;
        }
      }
    }
  }

  private void exchangeNodes(WeightedNode[] weightedNodeArray, int firstIndex, int secondIndex) {
    WeightedNode copyNode = weightedNodeArray[firstIndex];
    weightedNodeArray[firstIndex] = weightedNodeArray[secondIndex];
    weightedNodeArray[secondIndex] = copyNode;
  }

  private void reorderLayer(int layerIndex, WeightedNode[] weightedNodes) {
    ISimpleNode[] sortedLayer = new ISimpleNode[weightedNodes.length];
    for (int nodeIndex = 0; nodeIndex < weightedNodes.length; nodeIndex++) {
      sortedLayer[nodeIndex] = weightedNodes[nodeIndex].getNode();
    }
    graph.setNewLayerOrder(layerIndex, sortedLayer);
  }

  private WeightedNode[] getWeightedUpperLayerNodes(ISimpleNode[] upperLayer, ISimpleNode[] lowerLayer) {
    boolean[][] matrix = IncidentMatrixUtilities.buildMatrix(upperLayer, lowerLayer);
    WeightedNode[] weightedNodes = new WeightedNode[upperLayer.length];
    for (int rowIndex = 0; rowIndex < matrix.length; rowIndex++) {
      boolean[] rowVector = matrix[rowIndex];
      Double vectorCenter = BarycenterCalculator.calculateVectorCenter(rowVector);
      weightedNodes[rowIndex] = new WeightedNode(upperLayer[rowIndex], vectorCenter);
    }
    return weightedNodes;
  }

  private WeightedNode[] getWeightedLowerLayerNodes(ISimpleNode[] upperLayer, ISimpleNode[] lowerLayer) {
    boolean[][] matrix = IncidentMatrixUtilities.buildMatrix(upperLayer, lowerLayer);
    WeightedNode[] weightedNodes = new WeightedNode[lowerLayer.length];
    for (int columnIndex = 0; columnIndex < matrix[0].length; columnIndex++) {
      boolean[] columnVector = IncidentMatrixUtilities.getColumnVector(matrix, columnIndex);
      Double vectorCenter = BarycenterCalculator.calculateVectorCenter(columnVector);
      weightedNodes[columnIndex] = new WeightedNode(lowerLayer[columnIndex], vectorCenter);
    }
    return weightedNodes;
  }
}