package net.sf.anathema.campaign.presenter;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import net.sf.anathema.campaign.model.ISeries;
import net.sf.anathema.campaign.model.ISeriesContentModel;
import net.sf.anathema.campaign.module.SeriesTypeFilter;
import net.sf.anathema.campaign.presenter.view.ISeriesContentView;
import net.sf.anathema.campaign.view.IContentChangeListener;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.tree.IRepositoryTreeView;
import net.sf.anathema.framework.repository.tree.RepositoryTreeCellRenderer;
import net.sf.anathema.framework.repository.tree.RepositoryTreeModel;
import net.sf.anathema.framework.repository.tree.RepositoryTreePresenter;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.resources.IResources;

public class CampaignContentPresenter implements IPresenter {

  private final ISeriesContentView contentView;
  private final ISeries campaign;
  private final IAnathemaModel anathemaModel;
  private final IResources resources;

  public CampaignContentPresenter(
      ISeriesContentView contentView,
      IResources resources,
      ISeries campaign,
      IAnathemaModel anathemaModel) {
    this.contentView = contentView;
    this.resources = resources;
    this.campaign = campaign;
    this.anathemaModel = anathemaModel;
  }

  public void initPresentation() {
    final IRepositoryTreeView contentTree = contentView.addContentTree();
    final ISeriesContentModel contentModel = campaign.getContentModel();
    new RepositoryTreePresenter(
        resources,
        contentModel,
        contentTree,
        new RepositoryTreeCellRenderer(resources),
        "CampaignContent.ContentRoot").initPresentation(); //$NON-NLS-1$
    final IRepositoryTreeView repositoryTree = contentView.addRepositoryTree();
    new RepositoryTreePresenter(
        resources,
        new RepositoryTreeModel(
            anathemaModel.getRepository().getPrintNameFileAccess(),
            anathemaModel.getItemTypeRegistry(),
            new SeriesTypeFilter()),
        repositoryTree,
        new DisablingRepositoryTreeCellRenderer(resources, contentModel),
        "CampaignContent.RepositoryRoot").initPresentation(); //$NON-NLS-1$
    contentView.initGui(new CampaignContentViewProperties(resources));
    contentView.addContentChangeListener(new IContentChangeListener() {
      public void addRequested() {
        JTree tree = (JTree) (repositoryTree.getComponent());
        int[] selectionRows = tree.getSelectionRows();
        if (selectionRows != null && selectionRows.length != 0) {
          for (TreePath selectionPath : tree.getSelectionPaths()) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) (selectionPath.getLastPathComponent());
            if (node.getUserObject() instanceof PrintNameFile) {
              PrintNameFile item = (PrintNameFile) (node.getUserObject());
              contentModel.addItem(item);
            }
          }
          tree.clearSelection();
          tree.repaint();
          contentTree.getComponent().repaint();
        }
      }

      public void removeRequested() {
        JTree tree = (JTree) (contentTree.getComponent());
        int[] selectionRows = tree.getSelectionRows();
        if (selectionRows != null && selectionRows.length != 0) {
          for (TreePath selectionPath : tree.getSelectionPaths()) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) (selectionPath.getLastPathComponent());
            if (node.getUserObject() instanceof PrintNameFile) {
              PrintNameFile item = (PrintNameFile) (node.getUserObject());
              contentModel.removeItem(item);
            }
          }
          tree.clearSelection();
          tree.repaint();
          repositoryTree.getComponent().repaint();
        }
      }

      public void selectionChanged(JTree tree) {
        if (tree.isSelectionEmpty()) {
          return;
        }
        for (TreePath path : tree.getSelectionPaths()) {
          DefaultMutableTreeNode node = (DefaultMutableTreeNode) (path.getLastPathComponent());
          if (node.getUserObject() instanceof IItemType) {
            for (int childIndex = 0; childIndex < node.getChildCount(); childIndex++) {
              DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) (node.getChildAt(childIndex));
              tree.addSelectionPath(new TreePath(childNode.getPath()));
            }
            tree.removeSelectionPath(new TreePath(node.getPath()));
          }
        }
      }
    });
  }
}