package net.sf.anathema.campaign.presenter.view.plot;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public interface ITreeView {

  TreePath getPathForLocation(int x, int y);

  TreePath[] getSelectionPaths();

  DefaultMutableTreeNode getSelectedNode();

  void setSelectionPath(TreePath path);

  JTree getTreeComponent();

  boolean isRootNodeSelected();
}