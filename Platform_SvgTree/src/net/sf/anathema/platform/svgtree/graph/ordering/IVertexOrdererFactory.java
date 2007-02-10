package net.sf.anathema.platform.svgtree.graph.ordering;

import net.sf.anathema.platform.svgtree.graph.graph.IProperHierarchicalGraph;

public interface IVertexOrdererFactory {

  public IVertexOrderer createVertexOrderer(IProperHierarchicalGraph graph);
}