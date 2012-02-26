package net.sf.anathema.graph.layering;

import net.sf.anathema.graph.nodes.IRegularNode;

public interface ILayerer {

  public int layerGraph(IRegularNode[] acyclicGraph);
}