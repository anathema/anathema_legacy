package net.sf.anathema.graph.graph;

import net.sf.anathema.graph.nodes.ISimpleNode;

public class SingleNodeGraph implements IProperHierarchicalGraph {

  private final ISimpleNode node;

  @Override
  public SingleNodeGraph clone() {
    return new SingleNodeGraph(node);
  }

  public SingleNodeGraph(final ISimpleNode node) {
    this.node = node;
  }

  public IGraphType getType() {
    return GraphType.Single;
  }

  public ISimpleNode[] getNodesByLayer(final int layer) {
    if (layer == 1) {
      return new ISimpleNode[] { node };
    }
    return null;
  }

  public void setNewLayerOrder(final int layer, final ISimpleNode[] orderedNodes) {
    // nothing to do
  }

  public int getDeepestLayer() {
    return 1;
  }

  public int calculateTotalNumberOfCrossings() {
    return 0;
  }

  public int calculateNumberOfCrossings(final int upperLayerIndex) {
    return 0;
  }

  public boolean containsRoot(final int layer) {
    return true;
  }
}