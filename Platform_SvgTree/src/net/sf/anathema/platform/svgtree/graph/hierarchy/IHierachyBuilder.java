package net.sf.anathema.platform.svgtree.graph.hierarchy;

import net.sf.anathema.platform.svgtree.graph.nodes.IRegularNode;
import net.sf.anathema.platform.svgtree.graph.nodes.ISimpleNode;

public interface IHierachyBuilder {

  public ISimpleNode[] removeLongEdges(IRegularNode[] acyclicGraph);

}