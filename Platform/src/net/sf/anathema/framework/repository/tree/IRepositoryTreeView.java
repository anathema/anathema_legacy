package net.sf.anathema.framework.repository.tree;

import javax.swing.Action;
import javax.swing.JTree;

import net.sf.anathema.lib.gui.IView;

public interface IRepositoryTreeView extends IView {

  public void addActionButton(Action action);

  public JTree addTree();
}