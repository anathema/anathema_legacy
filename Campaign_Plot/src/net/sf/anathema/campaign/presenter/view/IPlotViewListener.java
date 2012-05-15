package net.sf.anathema.campaign.presenter.view;

import javax.swing.tree.DefaultMutableTreeNode;

public interface IPlotViewListener {

  void selectionChangedTo(DefaultMutableTreeNode node);

  void contentAddedRequested(DefaultMutableTreeNode node);

  void removeRequested(DefaultMutableTreeNode node);

  void moveToRequested(DefaultMutableTreeNode node, int index);

  void moveToRequested(DefaultMutableTreeNode oldParentNode, DefaultMutableTreeNode node, int index);
}