package net.sf.anathema.platform.svgtree.graph.layering;

import net.sf.anathema.platform.svgtree.graph.nodes.IRegularNode;
public interface ILayerer {

  public int layerGraph(IRegularNode[] acyclicGraph);
}