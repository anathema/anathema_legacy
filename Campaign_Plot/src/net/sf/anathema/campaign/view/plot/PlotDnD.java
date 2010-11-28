package net.sf.anathema.campaign.view.plot;

import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;

import javax.swing.JComponent;
import javax.swing.TransferHandler;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import net.sf.anathema.campaign.presenter.view.plot.ITreeView;

public class PlotDnD {

  private ITreeView treeView;
  protected DefaultMutableTreeNode dropTargetNode;
  private final PlotViewListenerControl listenerControl;

  public PlotDnD(ITreeView treeView, PlotViewListenerControl listenerControl) {
    this.treeView = treeView;
    this.listenerControl = listenerControl;
  }

  private DefaultMutableTreeNode getSelectedHierachyNode() {
    return treeView.getSelectedNode();
  }

  public void initDragAndDrop() {
    treeView.getTreeComponent().setDragEnabled(true);
    treeView.getTreeComponent().setTransferHandler(new TransferHandler() {

      @Override
      public boolean canImport(JComponent comp, DataFlavor[] transferFlavors) {
        for (DataFlavor flavor : transferFlavors) {
          if (!flavor.equals(PlotElementTransferable.FLAVOR)) {
            return false;
          }
        }
        return false;
      }

      @Override
      protected Transferable createTransferable(JComponent c) {
        if (treeView.isRootNodeSelected()) {
          return null;
        }
        DefaultMutableTreeNode draggedNode = getSelectedHierachyNode();
        return new PlotElementTransferable(draggedNode);
      }

      @Override
      public int getSourceActions(JComponent c) {
        return MOVE;
      }

      @Override
      public boolean importData(JComponent comp, Transferable transferable) {
        DefaultMutableTreeNode transferNode = null;
        try {
          transferNode = (DefaultMutableTreeNode) transferable.getTransferData(PlotElementTransferable.FLAVOR);
        }
        catch (Exception e) {
          if (e instanceof RuntimeException) {
            throw (RuntimeException) e;
          }
          throw new IllegalStateException(e);
        }
        if (transferNode == null) {
          return false;
        }
        if (dropTargetNode == null) {
          return false;
        }
        DefaultMutableTreeNode newParentNode = (DefaultMutableTreeNode) dropTargetNode.getParent();
        listenerControl.fireMoveToRequested(dropTargetNode, transferNode, treeView.getTreeComponent()
            .getModel()
            .getIndexOfChild(newParentNode, dropTargetNode));
        return true;
      }
    });
    new DropTarget(treeView.getTreeComponent(), DnDConstants.ACTION_MOVE, new DropTargetAdapter() {
      public void drop(DropTargetDropEvent dtde) {
        Point dropLocation = dtde.getLocation();
        TreePath pathForLocation = treeView.getTreeComponent().getPathForLocation(dropLocation.x, dropLocation.y);
        if (pathForLocation == null) {
          dropTargetNode = null;
        }
        else {
          dropTargetNode = (DefaultMutableTreeNode) pathForLocation.getLastPathComponent();
        }
        dtde.dropComplete(treeView.getTreeComponent().getTransferHandler().importData(
            treeView.getTreeComponent(),
            dtde.getTransferable()));
      }
    });
  }
}