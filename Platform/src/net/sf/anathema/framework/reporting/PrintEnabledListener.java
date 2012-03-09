package net.sf.anathema.framework.reporting;

import net.sf.anathema.framework.presenter.ItemManagementModelAdapter;
import net.sf.anathema.framework.repository.IItem;

import javax.swing.Action;

public class PrintEnabledListener extends ItemManagementModelAdapter {

  private final IReportRegistry reportRegistry;
  private final Action action;

  public PrintEnabledListener(Action action, IReportRegistry reportRegistry) {
    this.reportRegistry = reportRegistry;
    this.action = action;
  }

  @Override
  public void itemSelected(IItem item) {
    Report[] reports = reportRegistry.getReports(item);
    action.setEnabled(reports.length > 0);
  }
}
