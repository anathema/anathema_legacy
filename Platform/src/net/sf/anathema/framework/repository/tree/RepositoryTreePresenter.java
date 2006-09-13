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
    this.root = new DefaultMutableTreeNode(resources.getString(rootKey), true);
    this.treeModel = new DefaultTreeModel(root);
    repositoryModel.addRepositoryTreeModelListener(new IRepositoryTreeModelListener() {
      public void printNameFileAdded(PrintNameFile file) {
        addPrintNameFileToTree(file);
      }

      public void itemTypeAdded(IItemType type) {
        addItemTypeToTree(type);
      }

      public void printNameFileRemoved(PrintNameFile file) {
        removePrintNameFileFromTree(file);
      }
    });
  }

  public void initPresentation() {
    for (IItemType type : repositoryModel.getAllItemTypes()) {
      if (type.supportsRepository()) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(type);
        treeModel.insertNodeInto(node, root, 0);
        for (PrintNameFile file : repositoryModel.getPrintNameFiles(type)) {
          addPrintNameFileToTree(file);
        }
      }
    }
    treeView.init(treeModel, renderer);
  }

  private void addPrintNameFileToTree(PrintNameFile file) {
    for (int index = 0; index < root.getChildCount(); index++) {
      DefaultMutableTreeNode node = (DefaultMutableTreeNode) root.getChildAt(index);
      if (file.getItemType() == node.getUserObject()) {
        treeModel.insertNodeInto(new DefaultMutableTreeNode(file, false), node, node.getChildCount());
      }
    }
  }

  private void addItemTypeToTree(IItemType type) {
    treeModel.insertNodeInto(new DefaultMutableTreeNode(type, true), root, root.getChildCount());
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