package net.sf.anathema.charms.graph.graph;

public interface IGraphType {

  public void accept(IGraphTypeVisitor visitor);
}