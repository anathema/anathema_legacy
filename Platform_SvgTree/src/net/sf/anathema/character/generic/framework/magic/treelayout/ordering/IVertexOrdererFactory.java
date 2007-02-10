package net.sf.anathema.character.generic.framework.magic.treelayout.ordering;

import net.sf.anathema.character.generic.framework.magic.treelayout.graph.IProperHierarchicalGraph;

public interface IVertexOrdererFactory {

  public IVertexOrderer createVertexOrderer(IProperHierarchicalGraph graph);
}