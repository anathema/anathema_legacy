package net.sf.anathema.campaign.presenter.view.plot;

import net.sf.anathema.campaign.presenter.view.IPlotViewListener;
import net.sf.anathema.framework.itemdata.view.IBasicItemDescriptionView;
import net.sf.anathema.lib.gui.IView;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;

public interface IPlotView extends IView {

  void initGui(IPlotViewProperties properties);

  void addPlotViewListener(IPlotViewListener listener);

  IBasicItemDescriptionView initBasicItemDescriptionView();

  ITreeView createHierarchyTreeView();

  void initSeriesHierarchyView(TreeModel model, TreeCellRenderer renderer, String title);

  void setAddButtonEnabled(boolean enabled);

  void setRemoveButtonEnabled(boolean enabled);

  void setUpButtonEnabled(boolean enabled);

  void setDownButtonEnabled(boolean enabled);

  void expandNode(DefaultMutableTreeNode node);

  void collapseNode(DefaultMutableTreeNode node);

  void setHierarchieTreeCellRenderer(TreeCellRenderer renderer);

  void setSelectedHierarchyNode(DefaultMutableTreeNode node);
}