package net.sf.anathema.platform.svgtree.document;

import net.sf.anathema.graph.graph.IProperHierarchicalGraph;
import net.sf.anathema.platform.svgtree.document.visualizer.ICascadeVisualizer;

public interface VisualizerFactory {
  ICascadeVisualizer createForBottomUp(IProperHierarchicalGraph graph);

  ICascadeVisualizer createForInvertedTree(IProperHierarchicalGraph graph);

  ICascadeVisualizer createForTree(IProperHierarchicalGraph graph);

  ICascadeVisualizer createForSingle(IProperHierarchicalGraph graph);
}
