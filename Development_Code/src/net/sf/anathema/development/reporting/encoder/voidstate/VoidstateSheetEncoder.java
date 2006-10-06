package net.sf.anathema.development.reporting.encoder.voidstate;

import java.util.Map;

import net.sf.anathema.character.generic.framework.reporting.template.voidstate.ExaltVoidstateReportTemplate;
import net.sf.anathema.development.reporting.ReportBuilder;
import net.sf.anathema.development.reporting.encoder.AbstractPagedCharacterSheetEncoder;
import net.sf.anathema.development.reporting.encoder.ICharacterSheetPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.pages.VoidstateCharmPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.pages.VoidstateFirstPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.pages.VoidstateSecondPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.pages.VoidstateSpecialPageEncoder;
import net.sf.anathema.initialization.InitializationException;

public class VoidstateSheetEncoder extends AbstractPagedCharacterSheetEncoder {

  public VoidstateSheetEncoder() throws InitializationException {
    super(ReportBuilder.VOID_STATE_PAGE_FORMAT);
  }

  @Override
  protected ICharacterSheetPageEncoder[] getPageEncoders() {
    return new ICharacterSheetPageEncoder[] {
        new VoidstateFirstPageEncoder(getColumnWidth()),
        new VoidstateSecondPageEncoder(getColumnWidth()),
        new VoidstateSpecialPageEncoder(getColumnWidth()),
        new VoidstateCharmPageEncoder(getColumnWidth()) };
  }

  @Override
  protected void addParameterClasses(Map<String, String> parameterClasses) {
    new ExaltVoidstateReportTemplate(null, null, null).addParameterClasses(parameterClasses);
  }

  @Override
  protected String getReportName() {
    return "VoidstateCharacterSheet";
  }
}