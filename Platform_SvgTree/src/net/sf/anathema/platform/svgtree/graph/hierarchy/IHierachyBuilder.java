package net.sf.anathema.charms.graph.hierarchy;

import net.sf.anathema.charms.graph.nodes.IRegularNode;
import net.sf.anathema.charms.graph.nodes.ISimpleNode;

public interface IHierachyBuilder {

  public ISimpleNode[] removeLongEdges(IRegularNode[] acyclicGraph);

}