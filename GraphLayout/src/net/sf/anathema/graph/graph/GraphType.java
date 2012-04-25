package net.sf.anathema.graph.graph;

public enum GraphType implements IGraphType {
  DirectedGraph {
    @Override
    public void accept(IGraphTypeVisitor visitor) {
      visitor.visitDirectedGraph(this);
    }
  },
  InvertedTree {
    @Override
    public void accept(IGraphTypeVisitor visitor) {
      visitor.visitInvertedTree(this);
    }
  },
  Tree {
    @Override
    public void accept(IGraphTypeVisitor visitor) {
      visitor.visitTree(this);
    }
  },
  Single {
    @Override
    public void accept(IGraphTypeVisitor visitor) {
      visitor.visitSingle(this);
    }
  }
}