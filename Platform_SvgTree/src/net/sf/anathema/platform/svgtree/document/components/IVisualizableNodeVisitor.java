package net.sf.anathema.platform.svgtree.document.components;

public interface IVisualizableNodeVisitor {

  void visitHorizontalMetaNode(HorizontalMetaNode visitednode);

  void visitSingleNode(VisualizableNode visitednode);

  void visitDummyNode(VisualizableDummyNode visitednode);
}