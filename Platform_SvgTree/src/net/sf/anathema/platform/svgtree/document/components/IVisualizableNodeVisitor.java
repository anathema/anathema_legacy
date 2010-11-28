package net.sf.anathema.platform.svgtree.document.components;

public interface IVisualizableNodeVisitor {

  public void visitHorizontalMetaNode(HorizontalMetaNode visitednode);

  public void visitSingleNode(VisualizableNode visitednode);

  public void visitDummyNode(VisualizableDummyNode visitednode);
}