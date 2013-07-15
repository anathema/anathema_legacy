package net.sf.anathema.graph.ordering;

import net.sf.anathema.graph.graph.IProperHierarchicalGraph;

public interface IVertexOrdererFactory {

  IVertexOrderer createVertexOrderer(IProperHierarchicalGraph graph);
}