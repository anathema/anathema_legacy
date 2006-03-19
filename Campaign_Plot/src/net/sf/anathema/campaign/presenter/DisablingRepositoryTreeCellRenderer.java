package net.sf.anathema.campaign.presenter;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import net.sf.anathema.campaign.model.ISeriesContentModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.resources.IResources;

public class DisablingRepositoryTreeCellRenderer extends DefaultTreeCellRenderer {

  private final ISeriesContentModel model;
  private final IResources resources;

  public DisablingRepositoryTreeCellRenderer(IResources resources, ISeriesContentModel model) {
    this.resources = resources;
    this.model = model;
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
    Component component = super.getTreeCellRendererComponent(
        tree,
        value,
        sel && isLegal(userObject),
        expanded,
        leaf,
        row,
        focus && isLegal(userObject));
    component.setEnabled(isLegal(userObject));
    return component;
  }

  protected boolean isLegal(Object object) {
    for (IItemType type : model.getAllItemTypes()) {
      for (PrintNameFile file : model.getPrintNameFiles(type)) {
        if (file.equals(object)) {
          return false;
        }
      }
    }
    return true;
  }
}
