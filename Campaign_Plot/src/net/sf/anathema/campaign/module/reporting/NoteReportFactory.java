package net.sf.anathema.campaign.module.reporting;

import net.sf.anathema.campaign.reporting.NoteReport;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.initialization.IReportFactory;
import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.initialization.ReportFactory;
import net.sf.anathema.lib.resources.IResources;

@ReportFactory
public class NoteReportFactory implements IReportFactory {

  public Report[] createReport(IResources resources, IAnathemaModel model) {
    return new Report[]{new NoteReport()};
  }
}
