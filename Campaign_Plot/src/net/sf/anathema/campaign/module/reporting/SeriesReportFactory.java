package net.sf.anathema.campaign.module.reporting;

import net.sf.anathema.campaign.reporting.MultiColumnSeriesReport;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.initialization.IReportFactory;
import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.initialization.ReportFactoryAutoCollector;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;

@ReportFactoryAutoCollector
@Weight(weight = 0)
public class SeriesReportFactory implements IReportFactory {

  @Override
  public Report[] createReport(Resources resources, IApplicationModel model) {
    return new Report[]{new MultiColumnSeriesReport(resources)};
  }
}
