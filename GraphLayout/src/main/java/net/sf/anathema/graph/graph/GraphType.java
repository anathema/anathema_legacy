package net.sf.anathema.graph.graph;

public enum GraphType implements IGraphType {
  DirectedGraph {
    @Override
    public void accept(IGraphTypeVisitor visitor) {
      visitor.visitDirectedGraph();
    }
  },
  InvertedTree {
    @Override
    public void accept(IGraphTypeVisitor visitor) {
      visitor.visitInvertedTree();
    }
  },
  Tree {
    @Override
    public void accept(IGraphTypeVisitor visitor) {
      visitor.visitTree();
    }
  },
  Single {
    @Override
    public void accept(IGraphTypeVisitor visitor) {
      visitor.visitSingle();
    }
  }
}