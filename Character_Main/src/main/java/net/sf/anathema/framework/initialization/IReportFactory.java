package net.sf.anathema.framework.initialization;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.reporting.Report;

public interface IReportFactory {

  Report[] createReport(Environment environment, IApplicationModel model);
}
