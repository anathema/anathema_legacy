package net.sf.anathema.character.generic.framework.magic.treelayout.graph;

import net.sf.anathema.character.generic.framework.magic.treelayout.graph.type.GraphType;
import net.sf.anathema.character.generic.framework.magic.treelayout.graph.type.IGraphType;
import net.sf.anathema.character.generic.framework.magic.treelayout.nodes.ISimpleNode;

public class SingleNodeGraph implements IProperHierarchicalGraph {

  private final ISimpleNode node;

  @Override
  public SingleNodeGraph clone() {
    return new SingleNodeGraph(node);
  }

  public SingleNodeGraph(ISimpleNode node) {
    this.node = node;
  }

  public IGraphType getType() {
    return GraphType.Single;
  }

  public ISimpleNode[] getNodesByLayer(int layer) {
    if (layer == 1) {
      return new ISimpleNode[] { node };
    }
    return null;
  }

  public void setNewLayerOrder(int layer, ISimpleNode[] orderedNodes) {
    // nothing to do
  }

  public int getDeepestLayer() {
    return 1;
  }

  public int calculateTotalNumberOfCrossings() {
    return 0;
  }

  public int calculateNumberOfCrossings(int upperLayerIndex) {
    return 0;
  }
}