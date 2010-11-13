package net.sf.anathema.campaign.presenter.view.plot;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public interface ITreeView {

  public TreePath getPathForLocation(int x, int y);

  public TreePath[] getSelectionPaths();

  public DefaultMutableTreeNode getSelectedNode();

  public void setSelectionPath(TreePath path);

  public JTree getTreeComponent();

  public boolean isRootNodeSelected();
}