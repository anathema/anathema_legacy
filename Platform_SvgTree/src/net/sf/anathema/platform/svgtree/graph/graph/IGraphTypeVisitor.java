package net.sf.anathema.charms.graph.graph;

public interface IGraphTypeVisitor {

  public void visitDirectedGraph(IGraphType visitedType);

  public void visitInvertedTree(IGraphType visitedType);

  public void visitTree(IGraphType visitedType);

  public void visitSingle(IGraphType visitedType);
}