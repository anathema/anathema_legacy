package net.sf.anathema.campaign.presenter.view;

import javax.swing.tree.DefaultMutableTreeNode;

public interface IPlotViewListener {

  public void selectionChangedTo(DefaultMutableTreeNode node);

  public void contentAddedRequested(DefaultMutableTreeNode node);

  public void removeRequested(DefaultMutableTreeNode node);

  public void moveToRequested(DefaultMutableTreeNode node, int index);

  public void moveToRequested(DefaultMutableTreeNode oldParentNode, DefaultMutableTreeNode node, int index);
}