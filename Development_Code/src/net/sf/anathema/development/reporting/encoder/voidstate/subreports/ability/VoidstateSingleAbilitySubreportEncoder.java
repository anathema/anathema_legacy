package net.sf.anathema.development.reporting.encoder.voidstate.subreports.ability;

import java.util.Map;

import net.sf.anathema.character.generic.framework.reporting.IAbilityReportConstants;
import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.development.reporting.encoder.AbstractPagedCharacterSheetEncoder;
import net.sf.anathema.development.reporting.encoder.ICharacterSheetPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.format.IVoidStateFormatConstants;
import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.util.SubreportUtilities;
import net.sf.anathema.development.reporting.util.TraitEncoder;

public class VoidstateSingleAbilitySubreportEncoder extends AbstractPagedCharacterSheetEncoder implements
    IVoidStateFormatConstants {

  private final VoidstateBasicsEncoder basicsEncoder;
  private final TraitEncoder traitEncoder;

  public VoidstateSingleAbilitySubreportEncoder(VoidstateBasicsEncoder basicsEncoder, TraitEncoder traitEncoder) {
    super(SubreportUtilities.createPageFormat(VoidstateSingleAbilityPageEncoder.calculateExtents(basicsEncoder)));
    this.basicsEncoder = basicsEncoder;
    this.traitEncoder = traitEncoder;
  }

  @Override
  protected ICharacterSheetPageEncoder[] getPageEncoders() {
    return new ICharacterSheetPageEncoder[] { new VoidstateSingleAbilityPageEncoder(
        basicsEncoder,
        traitEncoder,
        EssenceTemplate.SYSTEM_ESSENCE_MAX) };
  }

  @Override
  protected void addParameterClasses(Map<String, String> parameterClasses) {
    parameterClasses.put(IAbilityReportConstants.PARAM_PRINT_CROSS, Boolean.class.getName());
    parameterClasses.put(IAbilityReportConstants.PARAM_FILL_RECTANGLE, Boolean.class.getName());
    parameterClasses.put(IAbilityReportConstants.PARAM_TRAIT_NAME, String.class.getName());
    parameterClasses.put(IAbilityReportConstants.PARAM_TRAIT_VALUE, Integer.class.getName());
  }

  @Override
  protected String getReportName() {
    return "VoidstateSingleAbilitySubreport";
  }
}