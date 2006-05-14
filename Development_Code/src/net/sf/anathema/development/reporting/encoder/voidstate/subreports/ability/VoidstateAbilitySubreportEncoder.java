package net.sf.anathema.development.reporting.encoder.voidstate.subreports.ability;

import java.awt.Point;
import java.util.Map;

import net.sf.anathema.character.generic.framework.reporting.parameters.AbilityParameterUtilities;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.development.reporting.encoder.AbstractPagedCharacterSheetEncoder;
import net.sf.anathema.development.reporting.encoder.ICharacterSheetPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.traits.VoidStateAbilityEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.util.SubreportUtilities;

public class VoidstateAbilitySubreportEncoder extends AbstractPagedCharacterSheetEncoder implements
    IVoidStateFormatConstants {

  private final VoidStateAbilityEncoder abilityEncoder;

  public VoidstateAbilitySubreportEncoder(VoidStateAbilityEncoder abiltyEncoder) {
    super(SubreportUtilities.createPageFormat(abiltyEncoder.calculateAbilityExtents(new Point(0, 0))));
    this.abilityEncoder = abiltyEncoder;
  }

  @Override
  protected ICharacterSheetPageEncoder[] getPageEncoders() {
    return new ICharacterSheetPageEncoder[] { new VoidstateAbilityPageEncoder(abilityEncoder) };
  }

  @Override
  protected void addParameterClasses(Map<String, String> parameterClasses) {
    AbilityParameterUtilities.addAbilityParameterClasses(parameterClasses);
  }

  @Override
  protected String getReportName() {
    return "VoidstateAbilitySubreport";
  }
}