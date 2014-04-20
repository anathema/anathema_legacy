package net.sf.anathema.framework.repository.tree;

public interface AgnosticTreeNode {
  AgnosticTreeNode addChildNode(Object type);

  Object getObject();

  void remove();

  AgnosticTreeNode[] getChildren();
}
