package net.sf.anathema.graph.ordering;

import net.sf.anathema.graph.graph.IProperHierarchicalGraph;

public interface IVertexOrdererFactory {

  public IVertexOrderer createVertexOrderer(IProperHierarchicalGraph graph);
}