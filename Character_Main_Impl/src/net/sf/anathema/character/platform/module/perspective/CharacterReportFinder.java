package net.sf.anathema.character.platform.module.perspective;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.reporting.DefaultReportFinder;
import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.resources.IResources;

public class CharacterReportFinder implements DefaultReportFinder {

  private IApplicationModel anathemaModel;
  private IResources resources;

  public CharacterReportFinder(IApplicationModel anathemaModel, IResources resources) {
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