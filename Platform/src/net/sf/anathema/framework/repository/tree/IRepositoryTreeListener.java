package net.sf.anathema.framework.repository.tree;

import javax.swing.tree.DefaultMutableTreeNode;

public interface IRepositoryTreeListener {

  public void nodeSelected(DefaultMutableTreeNode selectedHierachyNode);
}