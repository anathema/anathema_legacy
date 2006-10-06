package net.sf.anathema.development.reporting.encoder.voidstate.subreports.special;

import java.awt.Rectangle;
import java.util.Map;

import net.sf.anathema.development.reporting.encoder.AbstractPagedCharacterSheetEncoder;
import net.sf.anathema.development.reporting.encoder.ICharacterSheetPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.util.SubreportUtilities;
import net.sf.anathema.initialization.InitializationException;

public class VoidstateNullPageSubreportEncoder extends AbstractPagedCharacterSheetEncoder {

  public VoidstateNullPageSubreportEncoder() throws InitializationException {
    super(SubreportUtilities.createPageFormat(new Rectangle(0,0)));
  }

  @Override
  protected ICharacterSheetPageEncoder[] getPageEncoders() {
    return new ICharacterSheetPageEncoder[] { new VoidstateNullPageEncoder(0) };
  }

  @Override
  protected void addParameterClasses(Map<String, String> parameterClasses) {
    //Nothing to do
  }

  @Override
  protected String getReportName() {
    return "VoidstateNullPage";
  }
}