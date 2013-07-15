package net.sf.anathema.platform.tree.document.components;

public interface IVisualizableNodeVisitor {

  void visitHorizontalMetaNode(HorizontalMetaNode visitednode);

  void visitSingleNode(VisualizableNode visitednode);

  void visitDummyNode(VisualizableDummyNode visitednode);
}