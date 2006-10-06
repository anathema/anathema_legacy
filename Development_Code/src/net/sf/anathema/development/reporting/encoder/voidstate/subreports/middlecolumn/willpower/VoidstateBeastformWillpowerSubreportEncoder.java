package net.sf.anathema.development.reporting.encoder.voidstate.subreports.middlecolumn.willpower;

import java.util.Map;

import net.sf.anathema.character.generic.framework.reporting.parameters.AdvantageParameterUtilities;
import net.sf.anathema.development.reporting.encoder.AbstractPagedCharacterSheetEncoder;
import net.sf.anathema.development.reporting.encoder.ICharacterSheetPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.util.SubreportUtilities;
import net.sf.anathema.initialization.InitializationException;

public class VoidstateBeastformWillpowerSubreportEncoder extends AbstractPagedCharacterSheetEncoder {

  private final VoidstateBasicsEncoder basicsEncoder;

  public VoidstateBeastformWillpowerSubreportEncoder(VoidstateBasicsEncoder basicsEncoder)
      throws InitializationException {
    super(SubreportUtilities.createPageFormat(VoidstateBeastformWillpowerPageEncoder.getExtents(basicsEncoder)));
    this.basicsEncoder = basicsEncoder;
  }

  @Override
  protected ICharacterSheetPageEncoder[] getPageEncoders() {
    return new ICharacterSheetPageEncoder[] { new VoidstateBeastformWillpowerPageEncoder(basicsEncoder) };
  }

  @Override
  protected void addParameterClasses(Map<String, String> parameterClasses) {
    AdvantageParameterUtilities.addWillpowerParameterClasses(parameterClasses);
  }

  @Override
  protected String getReportName() {
    return "VoidstateBeastformWillpowerSubreport";
  }
}
