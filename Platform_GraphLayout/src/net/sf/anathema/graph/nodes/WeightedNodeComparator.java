package net.sf.anathema.graph.nodes;

import java.util.Comparator;

public class WeightedNodeComparator implements Comparator<WeightedNode> {

  public int compare(WeightedNode o1, WeightedNode o2) {
    if (o1.getWeight() == null || o2.getWeight() == null) {
      return 0;
    }
    double weightDifference = o1.getWeight() - o2.getWeight();
    if (weightDifference < 0) {
      return -1;
    }
    if (weightDifference > 0) {
      return 1;
    }
    return 0;
  }
}