package net.sf.anathema.campaign.view.util;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import net.sf.anathema.campaign.presenter.view.plot.ITreeView;

public class DefaultTreeView implements ITreeView {

  private final JTree tree;

  public DefaultTreeView(JTree tree) {
    this.tree = tree;
  }

  public TreePath getPathForLocation(int x, int y) {
    return tree.getPathForLocation(x, y);
  }

  public TreePath[] getSelectionPaths() {
    return tree.getSelectionPaths();
  }

  public void setSelectionPath(TreePath path) {
    tree.getSelectionModel().setSelectionPath(path);
  }

  public JTree getTreeComponent() {
    return tree;
  }

  public DefaultMutableTreeNode getSelectedNode() {
    TreePath selectionPath = tree.getSelectionModel().getSelectionPath();
    if (selectionPath == null) {
      return null;
    }
    return (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
  }

  public boolean isRootNodeSelected() {
    return getSelectedNode() == tree.getModel().getRoot();
  }
}