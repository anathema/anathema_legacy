package net.sf.anathema.development.reporting.encoder.voidstate.subreports.middlecolumn.virtues;

import java.util.Map;

import net.sf.anathema.character.generic.framework.reporting.parameters.AdvantageParameterUtilities;
import net.sf.anathema.character.reporting.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.development.reporting.encoder.AbstractPagedCharacterSheetEncoder;
import net.sf.anathema.development.reporting.encoder.ICharacterSheetPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.util.SubreportUtilities;
import net.sf.anathema.development.reporting.util.TraitEncoder;

public class VoidstateVirtuesSubreportEncoder extends AbstractPagedCharacterSheetEncoder implements
    IVoidStateFormatConstants {

  private final TraitEncoder traitEncoder;
  private final VoidstateBasicsEncoder basicsEncoder;

  public VoidstateVirtuesSubreportEncoder(VoidstateBasicsEncoder basicsEncoder, TraitEncoder traitEncoder) {
    super(SubreportUtilities.createPageFormat(VoidstateVirtuesPageEncoder.getExtents(basicsEncoder)));
    this.basicsEncoder = basicsEncoder;
    this.traitEncoder = traitEncoder;
  }

  @Override
  protected ICharacterSheetPageEncoder[] getPageEncoders() {
    return new ICharacterSheetPageEncoder[] { new VoidstateVirtuesPageEncoder(basicsEncoder, traitEncoder) };
  }

  @Override
  protected void addParameterClasses(Map<String, String> parameterClasses) {
    AdvantageParameterUtilities.addVirtueParameterClasses(parameterClasses);
  }

  @Override
  protected String getReportName() {
    return "VoidstateVirtuesSubreport";
  }
}
