package net.sf.anathema.framework.repository.tree;

import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;

import net.sf.anathema.lib.gui.IView;

public interface IRepositoryTreeView extends IView {

  public void init(TreeModel treeModel, TreeCellRenderer renderer);
}