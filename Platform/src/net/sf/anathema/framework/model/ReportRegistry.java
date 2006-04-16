package net.sf.anathema.framework.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.framework.reporting.IReportRegistry;
import net.sf.anathema.framework.reporting.jasper.IJasperReport;
import net.sf.anathema.framework.repository.IItem;

public class ReportRegistry implements IReportRegistry {

  private final List<IJasperReport> reports = new ArrayList<IJasperReport>();

  public ReportRegistry() {
    super();
  }

  public void addReport(IJasperReport report) {
    reports.add(report);
  }

  public IJasperReport[] getReports(IItem item) {
    List<IJasperReport> supportedReports = new ArrayList<IJasperReport>();
    for (IJasperReport report : reports) {
      if (report.supports(item)) {
        supportedReports.add(report);
      }
    }
    return supportedReports.toArray(new IJasperReport[supportedReports.size()]);
  }

}
