package net.sf.anathema.graph.graph;

public interface IGraphTypeVisitor {

  void visitDirectedGraph();

  void visitInvertedTree();

  void visitTree();

  void visitSingle();
}