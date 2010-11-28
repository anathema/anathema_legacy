package net.sf.anathema.graph.graph;

public enum GraphType implements IGraphType {
  DirectedGraph {
    public void accept(IGraphTypeVisitor visitor) {
      visitor.visitDirectedGraph(this);
    }
  },
  InvertedTree {
    public void accept(IGraphTypeVisitor visitor) {
      visitor.visitInvertedTree(this);
    }
  },
  Tree {
    public void accept(IGraphTypeVisitor visitor) {
      visitor.visitTree(this);
    }
  },
  Single {
    public void accept(IGraphTypeVisitor visitor) {
      visitor.visitSingle(this);
    }
  }
}