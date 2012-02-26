package net.sf.anathema.graph.graph;

public interface IGraphType {

  public void accept(IGraphTypeVisitor visitor);
}