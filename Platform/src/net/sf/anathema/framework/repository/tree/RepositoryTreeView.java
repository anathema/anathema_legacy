package net.sf.anathema.framework.repository.tree;

import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;

public class RepositoryTreeView implements IRepositoryTreeView {

  private JTree tree;

  public void init(TreeModel treeModel, TreeCellRenderer renderer) {
    this.tree = new JTree(treeModel);
    this.tree.setCellRenderer(renderer);
  }

  public JComponent getComponent() {
    return tree;
  }
}