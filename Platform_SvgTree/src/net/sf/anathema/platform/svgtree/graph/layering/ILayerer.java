package net.sf.anathema.charms.graph.layering;

import net.sf.anathema.charms.graph.nodes.IRegularNode;
public interface ILayerer {

  public int layerGraph(IRegularNode[] acyclicGraph);
}