package net.sf.anathema.graph.graph;

import net.sf.anathema.graph.nodes.ISimpleNode;

public interface IProperHierarchicalGraph extends LayeredGraph {

  void setNewLayerOrder(int layer, ISimpleNode[] orderedNodes);

  int calculateTotalNumberOfCrossings();

  int calculateNumberOfCrossings(int upperLayerIndex);

  IGraphType getType();

  IProperHierarchicalGraph clone();

  boolean containsRoot(int layer);
}
