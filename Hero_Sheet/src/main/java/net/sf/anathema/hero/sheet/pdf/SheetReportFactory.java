package net.sf.anathema.hero.sheet.pdf;

import com.google.common.collect.Lists;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.initialization.IReportFactory;
import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.framework.reporting.pdf.AbstractPdfReport;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.framework.HeroEnvironmentExtractor;
import net.sf.anathema.hero.sheet.preferences.PageSizePreference;
import net.sf.anathema.initialization.RegisteredReportFactory;
import net.sf.anathema.initialization.reflections.Weight;

import java.util.List;

@RegisteredReportFactory
@Weight(weight = 10)
public class SheetReportFactory implements IReportFactory {

  @Override
  public Report[] createReport(Environment environment, IApplicationModel model) {
    HeroEnvironment characterGenerics = HeroEnvironmentExtractor.getGenerics(model);
    PageSizePreference pageSizePreference = new PageSizePreference(environment);
    List<AbstractPdfReport> reports = Lists.newArrayList(new PortraitSimpleExaltSheetReport(environment, characterGenerics, pageSizePreference),
            new PortraitSimpleMortalSheetReport(environment, characterGenerics, pageSizePreference),
            new LandscapeExaltSheetReport(environment, characterGenerics, pageSizePreference));
    return reports.toArray(new Report[reports.size()]);
  }
}
