package net.sf.anathema.campaign.presenter.view.plot;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;

import net.sf.anathema.campaign.presenter.view.IPlotViewListener;
import net.sf.anathema.framework.itemdata.view.IBasicItemDescriptionView;
import net.sf.anathema.framework.presenter.view.ITabView;

public interface IPlotView extends ITabView<IPlotViewProperties> {

  public void addPlotViewListener(IPlotViewListener listener);

  public IBasicItemDescriptionView initBasicItemDescriptionView(String title);

  public ITreeView getHierarchyTreeView();

  public void initSeriesHierarchyView(TreeModel model, TreeCellRenderer renderer, String title);

  public void setAddButtonEnabled(boolean enabled);

  public void setRemoveButtonEnabled(boolean enabled);

  public void setUpButtonEnabled(boolean enabled);

  public void setDownButtonEnabled(boolean enabled);

  public void expandNode(DefaultMutableTreeNode node);

  public void collapseNode(DefaultMutableTreeNode node);

  public void updateHierarchieTreeCellRenderer(TreeCellRenderer renderer);

  public void setSelectedHierarchyNode(DefaultMutableTreeNode node);
}