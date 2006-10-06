package net.sf.anathema.development.reporting.encoder.voidstate.subreports.middlecolumn;

import java.util.Map;

import net.sf.anathema.character.generic.framework.reporting.template.voidstate.ExaltVoidstateReportTemplate;
import net.sf.anathema.development.reporting.encoder.AbstractPagedCharacterSheetEncoder;
import net.sf.anathema.development.reporting.encoder.ICharacterSheetPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.util.SubreportUtilities;
import net.sf.anathema.initialization.InitializationException;

public class BeastformMiddleColumnSubreportEncoder extends AbstractPagedCharacterSheetEncoder {
  private final VoidstateBasicsEncoder basicsEncoder;

  public BeastformMiddleColumnSubreportEncoder(VoidstateBasicsEncoder basicsEncoder) throws InitializationException {
    super(SubreportUtilities.createPageFormat(DefaultMiddleColumnPageEncoder.getExtents(basicsEncoder)));
    this.basicsEncoder = basicsEncoder;
  }

  @Override
  protected ICharacterSheetPageEncoder[] getPageEncoders() {
    return new ICharacterSheetPageEncoder[] { new BeastformMiddleColumnPageEncoder(basicsEncoder) };
  }

  @Override
  protected void addParameterClasses(Map<String, String> parameterClasses) {
    ExaltVoidstateReportTemplate.addMiddleColumnContentParameterClasses(parameterClasses);
  }

  @Override
  protected String getReportName() {
    return "BeastformMiddleColumnSubreport";
  }
}