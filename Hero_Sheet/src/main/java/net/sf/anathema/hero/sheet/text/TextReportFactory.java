package net.sf.anathema.hero.sheet.text;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.initialization.IReportFactory;
import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.framework.HeroEnvironmentExtractor;
import net.sf.anathema.initialization.RegisteredReportFactory;
import net.sf.anathema.framework.environment.dependencies.Weight;

@RegisteredReportFactory
@Weight(weight = 50)
public class TextReportFactory implements IReportFactory {

  @Override
  public Report[] createReport(Environment environment, IApplicationModel model) {
    HeroEnvironment characterGenerics = HeroEnvironmentExtractor.getGenerics(model);
    return new Report[]{new TextReport(environment, characterGenerics)};
  }
}
