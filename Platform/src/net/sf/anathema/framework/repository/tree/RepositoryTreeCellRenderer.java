package net.sf.anathema.framework.repository.tree;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import net.disy.commons.swing.ui.IObjectUi;
import net.sf.anathema.development.ObjectUiTreeCellRenderer;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.lib.resources.IResources;

public class RepositoryTreeCellRenderer extends ObjectUiTreeCellRenderer {

  private final IResources resources;

  public RepositoryTreeCellRenderer(IObjectUi ui, IResources resources) {
    super(ui);
    this.resources = resources;
  }

  @Override
  public Component getTreeCellRendererComponent(
      JTree tree,
      Object value,
      boolean sel,
      boolean expanded,
      boolean leaf,
      int row,
      boolean focus) {
    DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) value;
    leaf = !treeNode.getAllowsChildren();
    Object userObject = treeNode.getUserObject();
    if (userObject instanceof IItemType) {
      String itemTypeString = resources.getString("ItemType." + ((IItemType) userObject).getId() + ".PrintName"); //$NON-NLS-1$ //$NON-NLS-2$
      return super.getTreeCellRendererComponent(tree, itemTypeString, sel, expanded, leaf, row, focus);
    }
    Component component = super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, focus);
    return component;
  }
}