package net.sf.anathema.character.impl.module.reporting;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.impl.reporting.PageSize;
import net.sf.anathema.character.impl.reporting.PdfSheetReport;
import net.sf.anathema.framework.extension.IExtensionPoint;
import net.sf.anathema.framework.reporting.IReport;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public class A4SheetReportFactory extends AbstractCharacterReportFactory {

  public IReport[] createReport(IResources resources, IRegistry<String, IExtensionPoint> extensionPointRegistry) {
    ICharacterGenerics characterGenerics = getCharacterGenerics(extensionPointRegistry);
    return new IReport[] { new PdfSheetReport(resources, characterGenerics, PageSize.A4) };
  }
}