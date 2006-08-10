package net.sf.anathema.campaign.presenter.plot;

import java.awt.Dimension;

import javax.swing.Icon;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.campaign.presenter.view.plot.IPlotViewProperties;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.framework.value.IconToggleButton;
import net.sf.anathema.lib.resources.IResources;

public class PlotViewProperties implements IPlotViewProperties {

  private final BasicUi basicUi;
  private final IResources resources;

  public PlotViewProperties(IResources resources) {
    this.resources = resources;
    this.basicUi = new BasicUi(resources);
  }

  public Dimension initHierarchyRemoveAction(SmartAction action) {
    Icon icon = basicUi.getRemoveIcon();
    action.setIcon(icon);
    action.setToolTipText(resources.getString("SeriesPlot.RemoveToolTip")); //$NON-NLS-1$
    return IconToggleButton.getPreferredSize(icon);
  }

  public Dimension initHierarchyAddAction(SmartAction action) {
    Icon icon = basicUi.getAddIcon();
    action.setIcon(icon);
    action.setToolTipText(resources.getString("SeriesPlot.AddToolTip")); //$NON-NLS-1$    
    return IconToggleButton.getPreferredSize(icon);
  }

  public Dimension initHierarchyUpAction(SmartAction action) {
    Icon icon = basicUi.getUpArrowIcon();
    action.setIcon(icon);
    action.setToolTipText(resources.getString("SeriesPlot.UpToolTip")); //$NON-NLS-1$    
    return IconToggleButton.getPreferredSize(icon);
  }

  public Dimension initHierarchyDownAction(SmartAction action) {
    Icon icon = basicUi.getDownArrowIcon();
    action.setIcon(icon);
    action.setToolTipText(resources.getString("SeriesPlot.DownToolTip")); //$NON-NLS-1$    
    return IconToggleButton.getPreferredSize(icon);
  }
}