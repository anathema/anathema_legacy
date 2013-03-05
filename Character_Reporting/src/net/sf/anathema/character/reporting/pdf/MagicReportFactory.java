package net.sf.anathema.character.reporting.pdf;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.initialization.IReportFactory;
import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.initialization.ReportFactoryAutoCollector;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.IResources;

@ReportFactoryAutoCollector
@Weight(weight = 30)
public class MagicReportFactory implements IReportFactory {

  @Override
  public Report[] createReport(IResources resources, IApplicationModel model) {
    return new Report[]{new MagicReport(resources, model)};
  }
}
