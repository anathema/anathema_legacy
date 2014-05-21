package net.sf.anathema.hero.sheet.text;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.environment.dependencies.Weight;
import net.sf.anathema.character.framework.reporting.IReportFactory;
import net.sf.anathema.character.framework.reporting.Report;
import net.sf.anathema.initialization.RegisteredReportFactory;

@RegisteredReportFactory
@Weight(weight = 50)
public class TextReportFactory implements IReportFactory {

  @Override
  public Report[] createReport(Environment environment, IApplicationModel model) {
    return new Report[]{new TextReport(environment)};
  }
}
