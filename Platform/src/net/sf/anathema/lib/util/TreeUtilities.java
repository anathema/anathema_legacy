package net.sf.anathema.lib.util;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.util.ArrayList;
import java.util.List;

public class TreeUtilities {

  public static DefaultMutableTreeNode[] getSelectedHierachyNodes(JTree tree) {
    List<DefaultMutableTreeNode> list = new ArrayList<>();
    TreePath[] selectionPaths = tree.getSelectionPaths();
    if (selectionPaths == null) {
      return new DefaultMutableTreeNode[0];
    }
    for (TreePath path : selectionPaths) {
      list.add(getNode(path));
    }
    return list.toArray(new DefaultMutableTreeNode[list.size()]);
  }

  private static DefaultMutableTreeNode getNode(TreePath path) {
    if (path == null) {
      return null;
    }
    return (DefaultMutableTreeNode) path.getLastPathComponent();
  }
}