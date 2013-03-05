package net.sf.anathema.campaign.module.reporting;

import net.sf.anathema.campaign.reporting.NoteReport;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.initialization.IReportFactory;
import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.initialization.ReportFactoryAutoCollector;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.IResources;

@ReportFactoryAutoCollector
@Weight(weight = 0)
public class NoteReportFactory implements IReportFactory {

  @Override
  public Report[] createReport(IResources resources, IApplicationModel model) {
    return new Report[]{new NoteReport()};
  }
}
