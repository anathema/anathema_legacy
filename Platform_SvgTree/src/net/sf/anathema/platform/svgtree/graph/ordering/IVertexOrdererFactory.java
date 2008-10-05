package net.sf.anathema.charms.graph.ordering;

import net.sf.anathema.charms.graph.graph.IProperHierarchicalGraph;

public interface IVertexOrdererFactory {

  public IVertexOrderer createVertexOrderer(IProperHierarchicalGraph graph);
}