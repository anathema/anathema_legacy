package net.sf.anathema.graph.ordering;

import net.sf.anathema.graph.graph.IProperHierarchicalGraph;
import net.sf.anathema.graph.nodes.ISimpleNode;
import net.sf.anathema.graph.nodes.WeightedNode;

public class SugiyamaVertexOrderer extends AbstractVertexOrderer {

  public SugiyamaVertexOrderer(IProperHierarchicalGraph graph) {
    super(graph);
  }

  private static final int PHASE_ONE_COUNT = 5;
  private static final int PHASE_TWO_COUNT = 50;

  private void doPhaseOne() {
    for (int phaseCount = 0; phaseCount < PHASE_ONE_COUNT; phaseCount++) {
      if (isCrossingFree()) {
        return;
      }
      for (int upperLayerIndex = 1; upperLayerIndex < graph.getDeepestLayer(); upperLayerIndex++) {
        if (hasRemainingCrossings()) {
          sortLayerByIncomingEdgeBarycenter(upperLayerIndex + 1);
        }
      }
      for (int upperLayerIndex = graph.getDeepestLayer() - 1; upperLayerIndex >= 1; upperLayerIndex--) {
        if (hasRemainingCrossings()) {
          sortLayerByOutgoingEdgeBarycenter(upperLayerIndex);
        }
      }
    }
  }

  private void doReorderPhase() {
    for (int upperLayerIndex = 1; upperLayerIndex < graph.getDeepestLayer(); upperLayerIndex++) {
      if (hasRemainingCrossings()) {
        reorderLowerLayer(upperLayerIndex);
      }
    }
    for (int upperLayerIndex = graph.getDeepestLayer() - 1; upperLayerIndex >= 1; upperLayerIndex--) {
      if (hasRemainingCrossings()) {
        reorderUpperLayer(upperLayerIndex);
      }
    }
  }

  private boolean isMonoton(WeightedNode[] weightedLowerLayerNodes) {
    Double weight = new Double(Integer.MIN_VALUE);
    for (WeightedNode node : weightedLowerLayerNodes) {
      Double currentWeight = node.getWeight();
      if (currentWeight == null) {
        continue;
      }
      if (currentWeight < weight) {
        return false;
      }
    }
    return true;
  }

  public void processMultiLayerGraph() {
    doPhaseOne();
    for (int reorderCount = 0; reorderCount < PHASE_TWO_COUNT; reorderCount++) {
      if (isCrossingFree()) {
        return;
      }
      doReorderPhase();
    }
  }

  private void reorderLowerLayer(int upperLayerIndex) {
    ISimpleNode[] upperLayer = graph.getNodesByLayer(upperLayerIndex);
    ISimpleNode[] lowerLayer = graph.getNodesByLayer(upperLayerIndex + 1);
    WeightedNode[] weightedLowerLayerNodes = getNodesWeightedByIncomingEdges(upperLayer, lowerLayer);
    permutateEqualWeightedNodex(weightedLowerLayerNodes);
    WeightedNode[] weightedUpperLayerNodes = getNodesWeightedByOutgoingEdges(upperLayer, lowerLayer);
    if (!isMonoton(weightedUpperLayerNodes)) {
      doPhaseOne();
    }
  }

  private void reorderUpperLayer(int upperLayerIndex) {
    ISimpleNode[] upperLayer = graph.getNodesByLayer(upperLayerIndex);
    ISimpleNode[] lowerLayer = graph.getNodesByLayer(upperLayerIndex + 1);
    WeightedNode[] weightedUpperLayerNodes = getNodesWeightedByOutgoingEdges(upperLayer, lowerLayer);
    permutateEqualWeightedNodex(weightedUpperLayerNodes);
    WeightedNode[] weightedLowerLayerNodes = getNodesWeightedByIncomingEdges(upperLayer, lowerLayer);
    if (!isMonoton(weightedLowerLayerNodes)) {
      doPhaseOne();
    }
  }
}