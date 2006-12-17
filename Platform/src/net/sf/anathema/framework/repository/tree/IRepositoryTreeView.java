package net.sf.anathema.framework.repository.tree;

import javax.swing.Action;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;

import net.sf.anathema.lib.gui.IView;

public interface IRepositoryTreeView extends IView {

  public void initTree(TreeModel treeModel, TreeCellRenderer renderer);

  public void addActionButton(Action action);

  public void addRepositoryTreeListener(IRepositoryTreeListener repositoryTreeListener);
}