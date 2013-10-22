package net.sf.anathema.hero.sheet.pdf;

import com.google.common.collect.Lists;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.environment.dependencies.Weight;
import net.sf.anathema.framework.initialization.IReportFactory;
import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.framework.reporting.pdf.AbstractPdfReport;
import net.sf.anathema.hero.sheet.preferences.PageSizePreference;
import net.sf.anathema.initialization.RegisteredReportFactory;

import java.util.List;

@RegisteredReportFactory
@Weight(weight = 10)
public class SheetReportFactory implements IReportFactory {

  @Override
  public Report[] createReport(Environment environment, IApplicationModel model) {
    PageSizePreference pageSizePreference = new PageSizePreference(environment);
    List<AbstractPdfReport> reports = Lists.newArrayList(
            new PortraitSimpleExaltSheetReport(environment, pageSizePreference),
            new PortraitSimpleMortalSheetReport(environment, pageSizePreference),
            new LandscapeExaltSheetReport(environment, pageSizePreference));
    return reports.toArray(new Report[reports.size()]);
  }
}
