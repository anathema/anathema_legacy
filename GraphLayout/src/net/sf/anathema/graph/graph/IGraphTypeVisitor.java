package net.sf.anathema.graph.graph;

public interface IGraphTypeVisitor {

  void visitDirectedGraph(IGraphType visitedType);

  void visitInvertedTree(IGraphType visitedType);

  void visitTree(IGraphType visitedType);

  void visitSingle(IGraphType visitedType);
}