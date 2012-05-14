package net.sf.anathema.campaign.presenter.view.plot;

import net.sf.anathema.lib.gui.action.SmartAction;

public interface IPlotViewProperties {

  public void initHierarchyRemoveAction(SmartAction action);

  public void initHierarchyAddAction(SmartAction action);

  public void initHierarchyUpAction(SmartAction action);

  public void initHierarchyDownAction(SmartAction action);

  public String getBorderTitle();
}