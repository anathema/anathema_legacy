package net.sf.anathema.hero.spells.sheet.magicreport;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.character.framework.reporting.IReportFactory;
import net.sf.anathema.character.framework.reporting.Report;
import net.sf.anathema.initialization.RegisteredReportFactory;
import net.sf.anathema.framework.environment.dependencies.Weight;

@RegisteredReportFactory
@Weight(weight = 30)
public class MagicReportFactory implements IReportFactory {

  @Override
  public Report[] createReport(Environment environment, IApplicationModel model) {
    return new Report[]{new MagicReport(environment, model)};
  }
}
