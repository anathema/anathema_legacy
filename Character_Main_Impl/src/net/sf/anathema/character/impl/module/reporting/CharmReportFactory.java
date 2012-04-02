package net.sf.anathema.character.impl.module.reporting;

import net.sf.anathema.character.impl.reporting.MagicReport;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.initialization.IReportFactory;
import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.initialization.ReportFactory;
import net.sf.anathema.lib.resources.IResources;

@ReportFactory
public class CharmReportFactory implements IReportFactory {

  @Override
  public Report[] createReport(IResources resources, IAnathemaModel model) {
    return new Report[]{new MagicReport(resources, model)};
  }
}
