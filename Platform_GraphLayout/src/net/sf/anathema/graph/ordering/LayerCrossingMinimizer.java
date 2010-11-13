package net.sf.anathema.graph.ordering;

import net.sf.anathema.graph.graph.IProperHierarchicalGraph;
import net.sf.anathema.graph.nodes.ISimpleNode;
import net.sf.anathema.lib.lang.ArrayUtilities;
import net.sf.anathema.lib.lang.IntegerUtilities;

public class LayerCrossingMinimizer {

  private final IProperHierarchicalGraph graph;
  private final int upperLayer;

  public LayerCrossingMinimizer(IProperHierarchicalGraph graph, int upperLayer) {
    this.graph = graph;
    this.upperLayer = upperLayer;
  }

  public void createOptimum(int changeableLayerIndex) {
    int crossingCount = calculateCrossingCount();
    if (crossingCount == 0) {
      return;
    }
    ISimpleNode[] changeableLayerNodes = graph.getNodesByLayer(changeableLayerIndex);
    int permutableNodeCount = changeableLayerNodes.length;
    if (permutableNodeCount < 2) {
      return;
    }
    int[] originalIndices = ArrayUtilities.createIndexArray(crossingCount);
    int[] revertedIndices = IntegerUtilities.revert(originalIndices);
    crossingCount = tryReordering(changeableLayerIndex, originalIndices, revertedIndices, crossingCount);
    if (crossingCount == 0 || permutableNodeCount == 2) {
      return;
    }
    crossingCount = tryIndexSwapping(changeableLayerIndex, crossingCount, originalIndices);
  }

  private int tryIndexSwapping(int changeableLayerIndex, int crossingCount, int[] originalIndices) {
    for (int index = 0; index < originalIndices.length - 1; index++) {
      crossingCount = tryAllWithoutIndex(changeableLayerIndex, crossingCount, originalIndices, index);
    }
    return crossingCount;
  }

  private int tryAllWithoutIndex(int changeableLayerIndex, int crossingCount, int[] originalIndices, int fixedIndex) {
    int[] remainingIndices = new int[originalIndices.length - 1];
    for (int index = 0; index < fixedIndex; index++) {
      remainingIndices[index] = originalIndices[index];
    }
    for (int index = fixedIndex + 1; index < originalIndices.length; index++) {
      remainingIndices[index - 1] = originalIndices[index];
    }
    if (remainingIndices.length == 2) {
      int[] reversedRemainingIndices = IntegerUtilities.revert(remainingIndices);
      return tryReordering(changeableLayerIndex, remainingIndices, reversedRemainingIndices, crossingCount);
    }
    for (int remainingIndex = 0; remainingIndex < remainingIndices.length; remainingIndex++) {
      crossingCount = tryAllWithoutIndex(changeableLayerIndex, crossingCount, remainingIndices, remainingIndex);
    }
    return crossingCount;
  }

  private int tryReordering(int changeableLayerIndex, int[] originalIndices, int[] newIndices, int crossingCount) {
    ISimpleNode[] changeableLayer = graph.getNodesByLayer(changeableLayerIndex);
    ISimpleNode[] newOrder = new ISimpleNode[changeableLayer.length];
    System.arraycopy(changeableLayer, 0, newOrder, 0, newOrder.length);
    ArrayUtilities.reorder(newOrder, originalIndices, newIndices);
    graph.setNewLayerOrder(changeableLayerIndex, newOrder);
    int newCrossingCount = calculateCrossingCount();
    if (newCrossingCount <= crossingCount) {
      return newCrossingCount;
    }
    graph.setNewLayerOrder(changeableLayerIndex, changeableLayer);
    return crossingCount;
  }

  private int calculateCrossingCount() {
    return graph.calculateNumberOfCrossings(upperLayer);
  }
}