package net.sf.anathema.development.reporting.encoder.voidstate.subreports.health;

import java.util.Map;

import net.sf.anathema.character.generic.framework.reporting.parameters.HealthParameterUtilities;
import net.sf.anathema.development.reporting.encoder.AbstractPagedCharacterSheetEncoder;
import net.sf.anathema.development.reporting.encoder.ICharacterSheetPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.util.SubreportUtilities;
import net.sf.anathema.initialization.InitializationException;

public class VoidstateLunarHealthSubreportEncoder extends AbstractPagedCharacterSheetEncoder {
  public VoidstateLunarHealthSubreportEncoder() throws InitializationException {
    super(SubreportUtilities.createPageFormat(AbstractVoidstateHealthEncoder.calculateBounds()));
  }

  @Override
  protected ICharacterSheetPageEncoder[] getPageEncoders() {
    return new ICharacterSheetPageEncoder[] { new VoidstateLunarHealthPageEncoder() };
  }

  @Override
  protected void addParameterClasses(Map<String, String> parameterClasses) {
    HealthParameterUtilities.addHealthMoveParameterClasses(parameterClasses);
    HealthParameterUtilities.addHealthLevelParameterClasses(parameterClasses);
    HealthParameterUtilities.addPainToleranceParameterClasses(parameterClasses);
    HealthParameterUtilities.addSoakParameterClasses(parameterClasses);
  }

  @Override
  protected String getReportName() {
    return "VoidstateLunarHealthSubreport";
  }
}
