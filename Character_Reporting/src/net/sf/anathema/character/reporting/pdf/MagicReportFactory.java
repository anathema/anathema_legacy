package net.sf.anathema.character.reporting.pdf;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.initialization.IReportFactory;
import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.initialization.RegisteredReportFactory;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;

@RegisteredReportFactory
@Weight(weight = 30)
public class MagicReportFactory implements IReportFactory {

  @Override
  public Report[] createReport(Resources resources, IApplicationModel model) {
    return new Report[]{new MagicReport(resources, model)};
  }
}
