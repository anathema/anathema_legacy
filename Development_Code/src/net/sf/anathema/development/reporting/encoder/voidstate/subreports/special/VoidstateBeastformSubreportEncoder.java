package net.sf.anathema.development.reporting.encoder.voidstate.subreports.special;

import java.util.Map;

import net.sf.anathema.character.lunar.reporting.LunarVoidstateReportTemplate;
import net.sf.anathema.development.reporting.ReportBuilder;
import net.sf.anathema.development.reporting.encoder.AbstractPagedCharacterSheetEncoder;
import net.sf.anathema.development.reporting.encoder.ICharacterSheetPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.pages.VoidstateSpecialPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.util.SubreportUtilities;
import net.sf.anathema.initialization.InitializationException;

public class VoidstateBeastformSubreportEncoder extends AbstractPagedCharacterSheetEncoder {

  public VoidstateBeastformSubreportEncoder() throws InitializationException {
    super(SubreportUtilities.createPageFormat(VoidstateSpecialPageEncoder.getSpecialPageExtents()));
  }

  @Override
  protected ICharacterSheetPageEncoder[] getPageEncoders() {
    return new ICharacterSheetPageEncoder[] { new VoidstateBeastformPageEncoder(
        ReportBuilder.VOID_STATE_PAGE_FORMAT.getColumnWidth()) };
  }

  @Override
  protected void addParameterClasses(Map<String, String> parameterClasses) {
    new LunarVoidstateReportTemplate(null).addParameterClasses(parameterClasses);
  }

  @Override
  protected String getReportName() {
    return "VoidstateBeastformPage";
  }
}