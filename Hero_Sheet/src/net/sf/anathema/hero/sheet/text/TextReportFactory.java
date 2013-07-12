package net.sf.anathema.hero.sheet.text;

import net.sf.anathema.hero.framework.HeroEnvironmentExtractor;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.initialization.IReportFactory;
import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.initialization.RegisteredReportFactory;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;

@RegisteredReportFactory
@Weight(weight = 50)
public class TextReportFactory implements IReportFactory {

  @Override
  public Report[] createReport(Resources resources, IApplicationModel model) {
    HeroEnvironment characterGenerics = HeroEnvironmentExtractor.getGenerics(model);
    return new Report[]{new TextReport(resources, characterGenerics)};
  }
}
