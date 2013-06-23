package net.sf.anathema.character.platform.module.perspective;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.reporting.DefaultReportFinder;
import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.framework.repository.Item;
import net.sf.anathema.lib.resources.Resources;

public class CharacterReportFinder implements DefaultReportFinder {

  private IApplicationModel anathemaModel;
  private Resources resources;

  public CharacterReportFinder(IApplicationModel anathemaModel, Resources resources) {
    this.anathemaModel = anathemaModel;
    this.resources = resources;
  }

  public Report getDefaultReport(Item item) {
    String reportName = resources.getString("CharacterModule.Reporting.Sheet.Name");
    for (Report report : anathemaModel.getReportRegistry().getReports(item)) {
      if (reportName.equals(report.toString())) {
        return report;
      }
    }
    return null;
  }
}