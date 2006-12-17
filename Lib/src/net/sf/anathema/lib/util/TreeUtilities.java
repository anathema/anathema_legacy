package net.sf.anathema.lib.util;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class TreeUtilities {

  public static DefaultMutableTreeNode getSelectedHierachyNode(JTree tree) {
    TreePath selectionPath = tree.getSelectionPath();
    if (selectionPath == null) {
      return null;
    }
    return (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
  }
}