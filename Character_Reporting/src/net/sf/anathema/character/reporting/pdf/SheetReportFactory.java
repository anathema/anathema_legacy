package net.sf.anathema.character.reporting.pdf;

import com.google.common.collect.Lists;
import net.sf.anathema.character.generic.framework.CharacterGenericsExtractor;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.initialization.IReportFactory;
import net.sf.anathema.framework.module.preferences.PageSizePreference;
import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.framework.reporting.pdf.AbstractPdfReport;
import net.sf.anathema.initialization.ReportFactory;
import net.sf.anathema.lib.resources.IResources;

import java.util.List;

import static net.sf.anathema.framework.module.preferences.EnableBetaContentPreferencesElement.enableBetaContent;

@ReportFactory
public class SheetReportFactory implements IReportFactory {

  @Override
  public Report[] createReport(IResources resources, IAnathemaModel model) {
    ICharacterGenerics characterGenerics = CharacterGenericsExtractor.getGenerics(model);
    PageSizePreference pageSizePreference = new PageSizePreference();
    List<AbstractPdfReport> reports = Lists.newArrayList(
            new PortraitSimpleExaltSheetReport(resources, characterGenerics, pageSizePreference),
            new PortraitSimpleMortalSheetReport(resources, characterGenerics, pageSizePreference),
            new LandscapeExaltSheetReport(resources, characterGenerics, pageSizePreference));
    if (enableBetaContent()) {
      reports.add(new ExtendedSheetReport(resources, characterGenerics, pageSizePreference));
    }
    return reports.toArray(new Report[reports.size()]);
  }
}
