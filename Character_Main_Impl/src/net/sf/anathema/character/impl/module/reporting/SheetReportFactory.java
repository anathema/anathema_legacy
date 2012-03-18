package net.sf.anathema.character.impl.module.reporting;

import net.sf.anathema.character.generic.framework.CharacterGenericsExtractor;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.impl.reporting.SimpleExaltSheetReport;
import net.sf.anathema.character.impl.reporting.SimpleMortalSheetReport;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.initialization.IReportFactory;
import net.sf.anathema.framework.module.preferences.PageSizePreference;
import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.initialization.ReportFactory;
import net.sf.anathema.lib.resources.IResources;

@ReportFactory
public class SheetReportFactory implements IReportFactory {

  public Report[] createReport(IResources resources, IAnathemaModel model) {
    ICharacterGenerics characterGenerics = CharacterGenericsExtractor.getGenerics(model);
    PageSizePreference pageSizePreference = new PageSizePreference();
    return new Report[]{new SimpleExaltSheetReport(resources, characterGenerics, pageSizePreference),
            new SimpleMortalSheetReport(resources, characterGenerics, pageSizePreference)};
  }
}
