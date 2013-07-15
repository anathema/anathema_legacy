package net.sf.anathema.graph.graph;

public interface IGraphType {

  void accept(IGraphTypeVisitor visitor);
}