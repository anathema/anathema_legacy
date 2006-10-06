package net.sf.anathema.development.reporting.encoder.voidstate.subreports.combat.sequence;

import java.util.Map;

import net.sf.anathema.character.generic.framework.reporting.parameters.CombatParameterUtilities;
import net.sf.anathema.development.reporting.encoder.AbstractPagedCharacterSheetEncoder;
import net.sf.anathema.development.reporting.encoder.ICharacterSheetPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.subreports.middlecolumn.flaw.AbstractVoidstateFlawPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.util.SubreportUtilities;
import net.sf.anathema.initialization.InitializationException;

public class VoidstateSequenceSubreportEncoder extends AbstractPagedCharacterSheetEncoder {

  private VoidstateBasicsEncoder basicsEncoder;

  public VoidstateSequenceSubreportEncoder(VoidstateBasicsEncoder basicsEncoder) throws InitializationException {
    super(SubreportUtilities.createPageFormat(AbstractVoidstateFlawPageEncoder.calculateBounds(basicsEncoder)));
    this.basicsEncoder = basicsEncoder;
  }

  @Override
  protected ICharacterSheetPageEncoder[] getPageEncoders() {
    return new ICharacterSheetPageEncoder[] { new VoidstateSequencePageEncoder(basicsEncoder) };
  }

  @Override
  protected void addParameterClasses(Map<String, String> parameterClasses) {
    CombatParameterUtilities.addCombatStatsParameterClasses(parameterClasses);
  }

  @Override
  protected String getReportName() {
    return "VoidStateSequenceSubreport";
  }
}