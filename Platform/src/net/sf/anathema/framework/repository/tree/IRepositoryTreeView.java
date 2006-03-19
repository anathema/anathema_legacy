package net.sf.anathema.framework.repository.tree;

import javax.swing.JComponent;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;

public interface IRepositoryTreeView {

  public void init(TreeModel treeModel, TreeCellRenderer renderer);

  public JComponent getComponent();
}