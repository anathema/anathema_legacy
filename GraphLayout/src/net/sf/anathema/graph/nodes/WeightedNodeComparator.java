package net.sf.anathema.graph.nodes;

import java.util.Comparator;

public class WeightedNodeComparator implements Comparator<WeightedNode> {

  @Override
  public int compare(WeightedNode o1, WeightedNode o2) {
    if (o1.getWeight() == null || o2.getWeight() == null) {
      return 0;
    }
    return (int) (o1.getWeight() - o2.getWeight());
  }
}