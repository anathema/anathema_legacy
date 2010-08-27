package net.sf.anathema.graph.ordering;

import net.sf.anathema.graph.graph.IProperHierarchicalGraph;

public class LayerByLayerSweepVertexOrderer extends AbstractVertexOrderer {

  private static final int MAX_PHASE_COUNT = 20;

  public LayerByLayerSweepVertexOrderer(IProperHierarchicalGraph graph) {
    super(graph);
  }

  public void processMultiLayerGraph() {
    for (int phaseCount = 0; phaseCount < MAX_PHASE_COUNT; phaseCount++) {
      if (isCrossingFree()) {
        return;
      }
      processDownPhase();
      if (isCrossingFree()) {
        return;
      }
      processUpPhase();
    }
  }

  private void processUpPhase() {
    for (int index = graph.getDeepestLayer(); index > 1; index--) {
      if (isCrossingFree()) {
        return;
      }
      performUpPhase(index);
    }
  }

  private void processDownPhase() {
    for (int index = 1; index < graph.getDeepestLayer(); index++) {
      if (isCrossingFree()) {
        return;
      }
      performDownPhase(index);
    }
  }

  private void performDownPhase(int fixedLayerIndex) {
    int changeableLayer = fixedLayerIndex + 1;
    sortLayerByIncomingEdgeBarycenter(changeableLayer);
    if (graph.calculateNumberOfCrossings(fixedLayerIndex) == 0) {
      return;
    }
    minimizeCrossingsByReorderLowerLayer(fixedLayerIndex);
  }

  private void performUpPhase(int fixedLayerIndex) {
    sortLayerByOutgoingEdgeBarycenter(fixedLayerIndex - 1);
    if (graph.calculateNumberOfCrossings(fixedLayerIndex - 1) == 0) {
      return;
    }
    minimizeCrossingsByReorderUpperLayer(fixedLayerIndex);
  }

  private void minimizeCrossingsByReorderLowerLayer(int fixedLayerIndex) {
    new LayerCrossingMinimizer(graph, fixedLayerIndex).createOptimum(fixedLayerIndex + 1);
  }

  private void minimizeCrossingsByReorderUpperLayer(int fixedLayerIndex) {
    new LayerCrossingMinimizer(graph, (fixedLayerIndex - 1)).createOptimum(fixedLayerIndex - 1);
  }
}