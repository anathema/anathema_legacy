package net.sf.anathema.framework.initialization;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.lib.resources.IResources;

public interface IReportFactory {

  Report[] createReport(IResources resources, IAnathemaModel model);
}
