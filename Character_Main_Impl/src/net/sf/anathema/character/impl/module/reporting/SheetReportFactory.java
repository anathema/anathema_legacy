package net.sf.anathema.character.impl.module.reporting;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.ICharacterGenericsExtension;
import net.sf.anathema.framework.module.preferences.PageSizePreference;
import net.sf.anathema.character.impl.reporting.SimpleExaltSheetReport;
import net.sf.anathema.character.impl.reporting.SimpleMortalSheetReport;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.initialization.IReportFactory;
import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.initialization.ReportFactory;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

@ReportFactory
public class SheetReportFactory implements IReportFactory {

  protected final ICharacterGenerics getCharacterGenerics(IRegistry<String, IAnathemaExtension> extensionPointRegistry) {
    ICharacterGenericsExtension extension = (ICharacterGenericsExtension) extensionPointRegistry.get(ICharacterGenericsExtension.ID);
    return extension.getCharacterGenerics();
  }

  public Report[] createReport(IResources resources, IRegistry<String, IAnathemaExtension> extensionPointRegistry) {
    ICharacterGenerics characterGenerics = getCharacterGenerics(extensionPointRegistry);
    PageSizePreference pageSizePreference = new PageSizePreference();
    return new Report[]{new SimpleExaltSheetReport(resources, characterGenerics, pageSizePreference), new SimpleMortalSheetReport(resources,
            characterGenerics, pageSizePreference)
            //new ExtendedSheetReport(resources, characterGenerics, pageSizePreference)
    };
  }
}
