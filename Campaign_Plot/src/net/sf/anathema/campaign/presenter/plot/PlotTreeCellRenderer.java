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
import net.sf.anathema.lib.resources.IResources;

public class PlotTreeCellRenderer extends DefaultTreeCellRenderer {

  private final Map<String, Icon> iconsByTimeUnitId = new HashMap<String, Icon>();

  public PlotTreeCellRenderer(IResources resources) {
    iconsByTimeUnitId.put(PlotModel.ID_EPISODE, resources.getImageIcon("plot/Scrolls.gif")); //$NON-NLS-1$
    iconsByTimeUnitId.put(PlotModel.ID_SERIES, resources.getImageIcon("plot/BookStack.gif")); //$NON-NLS-1$
    iconsByTimeUnitId.put(PlotModel.ID_STORY, resources.getImageIcon("plot/Book.gif")); //$NON-NLS-1$
    iconsByTimeUnitId.put(PlotModel.ID_SCENE, resources.getImageIcon("plot/Scroll_Open.gif")); //$NON-NLS-1$
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