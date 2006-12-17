package net.sf.anathema.framework.repository.tree;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeCellRenderer;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.resources.IResources;

public class RepositoryTreePresenter implements IPresenter {

  private final DefaultMutableTreeNode root;
  private final DefaultTreeModel treeModel;
  private final IRepositoryTreeModel repositoryModel;
  private final IRepositoryTreeView treeView;
  private final TreeCellRenderer renderer;

  public RepositoryTreePresenter(
      IResources resources,
      IRepositoryTreeModel repositoryModel,
      IRepositoryTreeView treeView,
      TreeCellRenderer renderer,
      String rootKey) {
    this.repositoryModel = repositoryModel;
    this.treeView = treeView;
    this.renderer = renderer;
    this.root = new DefaultMutableTreeNode(resources.getString(rootKey) + " [" //$NON-NLS-1$
        + repositoryModel.getRepositoryPath()
        + "]", true); //$NON-NLS-1$
    this.treeModel = new DefaultTreeModel(root);
    repositoryModel.addRepositoryTreeModelListener(new IRepositoryTreeModelListener() {
      public void printNameFileAdded(PrintNameFile file) {
        addPrintNameFileToTree(file);
      }

      public void printNameFileRemoved(PrintNameFile file) {
        removePrintNameFileFromTree(file);
      }
    });
  }

  public void initPresentation() {
    initTree();
  }

  private void initTree() {
    for (IItemType type : repositoryModel.getAllItemTypes()) {
      if (type.supportsRepository()) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(type);
        treeModel.insertNodeInto(node, root, 0);
        for (PrintNameFile file : repositoryModel.getPrintNameFiles(type)) {
          addPrintNameFileToTree(file);
        }
      }
    }
    treeView.initTree(treeModel, renderer);
    treeView.addRepositoryTreeListener(new IRepositoryTreeListener() {
      public void nodeSelected(DefaultMutableTreeNode node) {
        if (node == null) {
          repositoryModel.setSelectedObject(null);
        }
        else {
          repositoryModel.setSelectedObject(node.getUserObject());
        }
      }
    });
  }

  private void addPrintNameFileToTree(PrintNameFile file) {
    for (int index = 0; index < root.getChildCount(); index++) {
      DefaultMutableTreeNode node = (DefaultMutableTreeNode) root.getChildAt(index);
      if (file.getItemType() == node.getUserObject()) {
        treeModel.insertNodeInto(new DefaultMutableTreeNode(file, false), node, node.getChildCount());
      }
    }
  }

  private void removePrintNameFileFromTree(PrintNameFile file) {
    MutableTreeNode foundNode = null;
    for (int typeIndex = 0; typeIndex < root.getChildCount(); typeIndex++) {
      DefaultMutableTreeNode typeNode = (DefaultMutableTreeNode) root.getChildAt(typeIndex);
      if (file.getItemType() == typeNode.getUserObject()) {
        for (int fileIndex = 0; fileIndex < typeNode.getChildCount(); fileIndex++) {
          DefaultMutableTreeNode fileNode = (DefaultMutableTreeNode) typeNode.getChildAt(fileIndex);
          if (file == fileNode.getUserObject()) {
            foundNode = fileNode;
            break;
          }
        }
        break;
      }
    }
    if (foundNode != null) {
      treeModel.removeNodeFromParent(foundNode);
    }
  }
}