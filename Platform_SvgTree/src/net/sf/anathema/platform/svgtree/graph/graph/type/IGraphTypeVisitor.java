package net.sf.anathema.platform.svgtree.graph.graph.type;

public interface IGraphTypeVisitor {

  public void visitDirectedGraph(IGraphType visitedType);

  public void visitInvertedTree(IGraphType visitedType);

  public void visitTree(IGraphType visitedType);

  public void visitSingle(IGraphType visitedType);
}