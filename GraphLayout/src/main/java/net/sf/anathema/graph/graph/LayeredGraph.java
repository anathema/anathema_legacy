package net.sf.anathema.graph.graph;

import net.sf.anathema.graph.nodes.ISimpleNode;

public interface LayeredGraph {
  ISimpleNode[] getNodesByLayer(int layer);

  int getDeepestLayer();
}
