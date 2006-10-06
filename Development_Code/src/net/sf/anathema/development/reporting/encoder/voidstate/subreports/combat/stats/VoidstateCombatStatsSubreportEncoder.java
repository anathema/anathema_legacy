package net.sf.anathema.development.reporting.encoder.voidstate.subreports.combat.stats;

import java.util.Map;

import net.sf.anathema.character.generic.framework.reporting.parameters.CombatParameterUtilities;
import net.sf.anathema.development.reporting.encoder.AbstractPagedCharacterSheetEncoder;
import net.sf.anathema.development.reporting.encoder.ICharacterSheetPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.util.SubreportUtilities;
import net.sf.anathema.initialization.InitializationException;

public class VoidstateCombatStatsSubreportEncoder extends AbstractPagedCharacterSheetEncoder {

  private final VoidstateBasicsEncoder basicsEncoder;

  public VoidstateCombatStatsSubreportEncoder(VoidstateBasicsEncoder basicsEncoder) throws InitializationException {
    super(SubreportUtilities.createPageFormat(VoidstateCombatStatsPageEncoder.calculateExtents(basicsEncoder)));
    this.basicsEncoder = basicsEncoder;
  }

  @Override
  protected ICharacterSheetPageEncoder[] getPageEncoders() {
    return new ICharacterSheetPageEncoder[] { new VoidstateCombatStatsPageEncoder(basicsEncoder) };
  }

  @Override
  protected void addParameterClasses(Map<String, String> parameterClasses) {
    CombatParameterUtilities.addCombatStatsParameterClasses(parameterClasses);
  }

  @Override
  protected String getReportName() {
    return "VoidstateCombatStatsSubreport";
  }
}