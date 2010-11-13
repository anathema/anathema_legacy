package net.sf.anathema.platform.svgtree.graph.graph;

import net.sf.anathema.platform.svgtree.graph.nodes.ISimpleNode;

public interface IProperHierarchicalGraph {

  public ISimpleNode[] getNodesByLayer(int layer);

  public void setNewLayerOrder(int layer, ISimpleNode[] orderedNodes);

  public int getDeepestLayer();

  public int calculateTotalNumberOfCrossings();

  public int calculateNumberOfCrossings(int upperLayerIndex);

  public IGraphType getType();

  public IProperHierarchicalGraph clone();

  public boolean containsRoot(int layer);
}