package net.sf.anathema.framework.reporting;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.resources.IResources;

public class DefaultReportFinder {

  private IAnathemaModel anathemaModel;
  private IResources resources;

  public DefaultReportFinder(IAnathemaModel anathemaModel, IResources resources) {
    this.anathemaModel = anathemaModel;
    this.resources = resources;
  }

  public Report getDefaultReport(IItem item) {
    String reportName = resources.getString("CharacterModule.Reporting.Sheet.Name");
    for (Report report : anathemaModel.getReportRegistry().getReports(item)) {
      if (reportName.equals(report.toString())) {
        return report;
      }
    }
    return null;
  }
}