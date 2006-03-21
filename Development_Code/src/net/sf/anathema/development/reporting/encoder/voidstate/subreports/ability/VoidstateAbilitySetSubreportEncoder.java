package net.sf.anathema.development.reporting.encoder.voidstate.subreports.ability;

import java.util.Map;

import net.sf.anathema.character.generic.framework.reporting.parameters.AbilityParameterUtilities;
import net.sf.anathema.development.reporting.encoder.AbstractPagedCharacterSheetEncoder;
import net.sf.anathema.development.reporting.encoder.ICharacterSheetPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.format.IVoidStateFormatConstants;
import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.util.SubreportUtilities;

public class VoidstateAbilitySetSubreportEncoder extends AbstractPagedCharacterSheetEncoder implements
    IVoidStateFormatConstants {

  private final VoidstateBasicsEncoder basicsEncoder;

  public VoidstateAbilitySetSubreportEncoder(VoidstateBasicsEncoder basicsEncoder) {
    super(SubreportUtilities.createPageFormat(VoidstateAbilitySetPageEncoder.calculateExtents(basicsEncoder)));
    this.basicsEncoder = basicsEncoder;
  }

  @Override
  protected ICharacterSheetPageEncoder[] getPageEncoders() {
    return new ICharacterSheetPageEncoder[] { new VoidstateAbilitySetPageEncoder(basicsEncoder) };
  }

  @Override
  protected void addParameterClasses(Map<String, String> parameterClasses) {
    AbilityParameterUtilities.addRevisedAbilityParameterClasses(parameterClasses);
  }

  @Override
  protected String getReportName() {
    return "VoidstateAbilitySetSubreport";
  }
}