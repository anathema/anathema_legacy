package net.sf.anathema.campaign.toolbar;

import net.sf.anathema.campaign.item.ItemManagementModelAdapter;
import net.sf.anathema.framework.reporting.IReportRegistry;
import net.sf.anathema.framework.reporting.Report;
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
