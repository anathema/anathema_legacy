package net.sf.anathema.graph.graph;

import net.sf.anathema.graph.nodes.ISimpleNode;
import net.sf.anathema.lib.exception.UnreachableCodeReachedException;

public class SingleNodeGraph implements IProperHierarchicalGraph, Cloneable {

  private final ISimpleNode node;

  @Override
  public SingleNodeGraph clone() {
    try {
      return (SingleNodeGraph)super.clone();
    } catch (CloneNotSupportedException e) {
      throw new UnreachableCodeReachedException(e);
    }
  }

  public SingleNodeGraph(final ISimpleNode node) {
    this.node = node;
  }

  @Override
  public IGraphType getType() {
    return GraphType.Single;
  }

  @Override
  public ISimpleNode[] getNodesByLayer(final int layer) {
    if (layer == 1) {
      return new ISimpleNode[] { node };
    }
    return null;
  }

  @Override
  public void setNewLayerOrder(final int layer, final ISimpleNode[] orderedNodes) {
    // nothing to do
  }

  @Override
  public int getDeepestLayer() {
    return 1;
  }

  @Override
  public int calculateTotalNumberOfCrossings() {
    return 0;
  }

  @Override
  public int calculateNumberOfCrossings(final int upperLayerIndex) {
    return 0;
  }

  @Override
  public boolean containsRoot(final int layer) {
    return true;
  }
}