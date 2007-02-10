package net.sf.anathema.charmtree.provider.components.nodes;

public interface IVisualizableNodeVisitor {

  public void visitHorizontalMetaNode(HorizontalMetaNode visitednode);

  public void visitSingleNode(VisualizableNode visitednode);

  public void visitDummyNode(VisualizableDummyNode visitednode);
}