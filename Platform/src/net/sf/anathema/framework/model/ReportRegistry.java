package net.sf.anathema.framework.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.anathema.framework.reporting.IReport;
import net.sf.anathema.framework.reporting.IReportRegistry;
import net.sf.anathema.framework.repository.IItem;

public class ReportRegistry implements IReportRegistry {

  private final List<IReport> reports = new ArrayList<IReport>();

  public ReportRegistry() {
    super();
  }

  public void addReports(IReport... newReports) {
    Collections.addAll(this.reports, newReports);
  }

  public IReport[] getReports(IItem item) {
    List<IReport> supportedReports = new ArrayList<IReport>();
    for (IReport report : reports) {
      if (report.supports(item)) {
        supportedReports.add(report);
      }
    }
    return supportedReports.toArray(new IReport[supportedReports.size()]);
  }
}