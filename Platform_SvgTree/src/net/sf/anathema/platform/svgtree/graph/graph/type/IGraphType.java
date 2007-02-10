package net.sf.anathema.platform.svgtree.graph.graph.type;

public interface IGraphType {

  public void accept(IGraphTypeVisitor visitor);
}