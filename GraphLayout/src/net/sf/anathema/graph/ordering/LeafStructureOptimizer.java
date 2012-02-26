package net.sf.anathema.graph.ordering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeafStructureOptimizer<T extends IStructureNode> {

  public List<T> optimize(T[] layer) {
    for (T node : layer) {
      if (node.hasMultipleParents()) {
        return Arrays.asList(layer);
      }
    }
    List<T> leaves = new ArrayList<T>();
    List<T> parents = new ArrayList<T>();
    for (T node : layer) {
      if (node.isLeafNode()) {
        leaves.add(node);
      }
      else {
        parents.add(node);
      }
    }
    for (int leafIndex = 0; leafIndex < leaves.size(); leafIndex++) {
      boolean isEvenIndex = leafIndex % 2 == 0;
      T leaf = leaves.get(leafIndex);
      if (isEvenIndex) {
        parents.add(0, leaf);
      }
      else {
        parents.add(leaf);
      }
    }
    return parents;
  }
}