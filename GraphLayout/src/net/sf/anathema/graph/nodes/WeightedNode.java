package net.sf.anathema.graph.nodes;

public class WeightedNode {

  private final ISimpleNode node;
  private final Double weight;

  public ISimpleNode getNode() {
    return node;
  }

  public Double getWeight() {
    return weight;
  }

  public WeightedNode(ISimpleNode node, Double weight) {
    this.node = node;
    this.weight = weight;
  }

  @Override
  public String toString() {
    return node + "(" + weight + ")"; //$NON-NLS-1$ //$NON-NLS-2$
  }
}