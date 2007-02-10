package net.sf.anathema.platform.svgtree.graph.graph;

public interface IGraphType {

  public void accept(IGraphTypeVisitor visitor);
}