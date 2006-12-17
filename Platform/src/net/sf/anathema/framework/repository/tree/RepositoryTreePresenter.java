package net.sf.anathema.framework.repository.tree;

import java.awt.Component;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeCellRenderer;

import net.disy.commons.core.message.Message;
import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.dialog.message.MessageDialogFactory;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.gui.dialog.ConfigurableVetor;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.IResources;

public class RepositoryTreePresenter implements IPresenter {

  private final DefaultMutableTreeNode root;
  private final DefaultTreeModel treeModel;
  private final IRepositoryTreeModel repositoryModel;
  private final IRepositoryTreeView treeView;
  private final TreeCellRenderer renderer;
  private final IResources resources;
  private Object currentlySelectedObject;

  public RepositoryTreePresenter(
      IResources resources,
      IRepositoryTreeModel repositoryModel,
      IRepositoryTreeView treeView,
      TreeCellRenderer renderer,
      String rootKey) {
    this.resources = resources;
    this.repositoryModel = repositoryModel;
    this.treeView = treeView;
    this.renderer = renderer;
    this.root = new DefaultMutableTreeNode(resources.getString(rootKey), true);
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
    final SmartAction action = new SmartAction(new BasicUi(resources).getRemoveIcon()) {
      @Override
      protected void execute(Component parentComponent) {
        String message = resources.getString("AnathemaCore.Tools.RepositoryView.DeleteMessage"); //$NON-NLS-1$
        String okButton = resources.getString("AnathemaCore.Tools.RepositoryView.DeleteOk"); //$NON-NLS-1$
        ConfigurableVetor vetor = new ConfigurableVetor(parentComponent, message, okButton);
        if (vetor.vetos()) {
          return;
        }
        try {
          repositoryModel.deleteItem(currentlySelectedObject);
        }
        catch (RepositoryException e) {
          MessageDialogFactory.showMessageDialog(parentComponent, new Message(
              resources.getString("AnathemaCore.Tools.RepositoryView.DeleteError"), e)); //$NON-NLS-1$
          Logger.getLogger(getClass()).error(e);
        }
      }
    };
    treeView.addActionButton(action);
    treeView.addRepositoryTreeListener(new IRepositoryTreeListener() {
      public void nodeSelected(DefaultMutableTreeNode node) {
        if (node == null) {
          currentlySelectedObject = null;
          action.setEnabled(false);
          return;
        }
        currentlySelectedObject = node.getUserObject();
        action.setEnabled(repositoryModel.canBeDeleted(currentlySelectedObject));
      }
    });
    action.setEnabled(false);
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