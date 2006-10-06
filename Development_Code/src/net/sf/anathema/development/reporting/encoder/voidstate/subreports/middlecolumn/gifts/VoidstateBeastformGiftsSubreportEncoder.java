package net.sf.anathema.development.reporting.encoder.voidstate.subreports.middlecolumn.gifts;

import java.util.Map;

import net.sf.anathema.character.generic.framework.reporting.template.voidstate.ExaltVoidstateReportTemplate;
import net.sf.anathema.development.reporting.encoder.AbstractPagedCharacterSheetEncoder;
import net.sf.anathema.development.reporting.encoder.ICharacterSheetPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.columns.IOneColumnEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.util.SubreportUtilities;
import net.sf.anathema.initialization.InitializationException;

public class VoidstateBeastformGiftsSubreportEncoder extends AbstractPagedCharacterSheetEncoder {

  private IOneColumnEncoder columnEncoder;

  public VoidstateBeastformGiftsSubreportEncoder(IOneColumnEncoder columnEncoder) throws InitializationException {
    super(SubreportUtilities.createPageFormat(VoidstateBeastformGiftsPageEncoder.getExtents(columnEncoder)));
    this.columnEncoder = columnEncoder;
  }

  @Override
  protected ICharacterSheetPageEncoder[] getPageEncoders() {
    return new ICharacterSheetPageEncoder[] { new VoidstateBeastformGiftsPageEncoder(columnEncoder) };
  }

  @Override
  protected void addParameterClasses(Map<String, String> parameterClasses) {
    ExaltVoidstateReportTemplate.addGiftDataSource(parameterClasses);
  }

  @Override
  protected String getReportName() {
    return "VoidstateBeastformGiftsSubreport";
  }

}
