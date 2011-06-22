package net.sf.anathema.campaign.presenter.plot;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import net.sf.anathema.campaign.concrete.plot.PlotModel;
import net.sf.anathema.campaign.model.plot.IPlotElement;
import net.sf.anathema.campaign.module.PlotUI;
import net.sf.anathema.lib.resources.IResources;

public class PlotTreeCellRenderer extends DefaultTreeCellRenderer {

  private static final long serialVersionUID = -2035781364025451688L;
  private final Map<String, Icon> iconsByTimeUnitId = new HashMap<String, Icon>();

  public PlotTreeCellRenderer(IResources resources) {
    PlotUI plotUI = new PlotUI(resources);
    iconsByTimeUnitId.put(PlotModel.ID_EPISODE, plotUI.getEpisodeIcon());
    iconsByTimeUnitId.put(PlotModel.ID_SERIES, plotUI.getSeriesIcon());
    iconsByTimeUnitId.put(PlotModel.ID_STORY, plotUI.getStoryIcon());
    iconsByTimeUnitId.put(PlotModel.ID_SCENE, plotUI.getSceneIcon());
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
    Object userObject = ((DefaultMutableTreeNode) value).getUserObject();
    if (!(userObject instanceof IPlotElement)) {
      return super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, focus);
    }
    IPlotElement plotElement = (IPlotElement) userObject;
    String text = plotElement.getDescription().getName().getText();
    Icon icon = iconsByTimeUnitId.get(plotElement.getTimeUnit().getId());
    JLabel textLabel = new JLabel(text, icon, SwingConstants.LEFT);
    textLabel.setOpaque(true);
    textLabel.setBackground(sel ? getBackgroundSelectionColor() : getBackgroundNonSelectionColor());
    return textLabel;
  }
}