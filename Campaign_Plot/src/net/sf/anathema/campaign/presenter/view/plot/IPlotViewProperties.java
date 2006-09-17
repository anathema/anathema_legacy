package net.sf.anathema.campaign.presenter.view.plot;

import java.awt.Dimension;

import net.disy.commons.swing.action.SmartAction;

public interface IPlotViewProperties {

  public Dimension initHierarchyRemoveAction(SmartAction action);

  public Dimension initHierarchyAddAction(SmartAction action);

  public Dimension initHierarchyUpAction(SmartAction action);

  public Dimension initHierarchyDownAction(SmartAction action);

  public String getBorderTitle();
}