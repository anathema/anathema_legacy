package net.sf.anathema.campaign.presenter.plot;

import net.sf.anathema.campaign.presenter.view.plot.IPlotViewProperties;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.Icon;

public class PlotViewProperties implements IPlotViewProperties {

  private final BasicUi basicUi = new BasicUi();
  private final Resources resources;

  public PlotViewProperties(Resources resources) {
    this.resources = resources;
  }

  @Override
  public void initHierarchyRemoveAction(SmartAction action) {
    Icon icon = basicUi.getRemoveIcon();
    action.setIcon(icon);
    action.setToolTipText(resources.getString("SeriesPlot.RemoveToolTip"));
  }

  @Override
  public void initHierarchyAddAction(SmartAction action) {
    Icon icon = basicUi.getAddIcon();
    action.setIcon(icon);
    action.setToolTipText(resources.getString("SeriesPlot.AddToolTip"));
  }

  @Override
  public void initHierarchyUpAction(Tool action) {
    action.setIcon(basicUi.getUpArrowIconPath());
    action.setTooltip(resources.getString("SeriesPlot.UpToolTip"));
  }

  @Override
  public void initHierarchyDownAction(SmartAction action) {
    Icon icon = basicUi.getDownArrowIcon();
    action.setIcon(icon);
    action.setToolTipText(resources.getString("SeriesPlot.DownToolTip"));
  }

  @Override
  public String getBorderTitle() {
    return resources.getString("CampaignDescription.BorderTitle");
  }
}