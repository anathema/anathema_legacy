package net.sf.anathema.development.reporting.encoder.voidstate.subreports.ability;

import java.util.Map;

import net.sf.anathema.character.generic.framework.reporting.IAbilityReportConstants;
import net.sf.anathema.character.reporting.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.development.reporting.encoder.AbstractPagedCharacterSheetEncoder;
import net.sf.anathema.development.reporting.encoder.ICharacterSheetPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.util.SubreportUtilities;
import net.sf.anathema.framework.reporting.IReportDataSource;
import net.sf.jasperreports.engine.JasperReport;

public class VoidstateTenAbilityGroupSubreportEncoder extends AbstractPagedCharacterSheetEncoder implements
    IVoidStateFormatConstants {

  private final VoidstateBasicsEncoder basicsEncoder;

  public VoidstateTenAbilityGroupSubreportEncoder(VoidstateBasicsEncoder basicsEncoder) {
    super(SubreportUtilities.createPageFormat(VoidstateTenAbilityGroupPageEncoder.calculateExtents(basicsEncoder)));
    this.basicsEncoder = basicsEncoder;
  }

  @Override
  protected ICharacterSheetPageEncoder[] getPageEncoders() {
    return new ICharacterSheetPageEncoder[] { new VoidstateTenAbilityGroupPageEncoder(basicsEncoder) };
  }

  @Override
  protected void addParameterClasses(Map<String, String> parameterClasses) {
    parameterClasses.put(IAbilityReportConstants.PARAM_GROUP_NAME, String.class.getName());
    parameterClasses.put(IAbilityReportConstants.PARAM_ABILITYGROUP_DATASOURCE, IReportDataSource.class.getName());
    parameterClasses.put(IAbilityReportConstants.SUBREPORT_SINGLE_ABILITY, JasperReport.class.getName());
  }

  @Override
  protected String getReportName() {
    return "VoidstateTenAbilityGroupSubreport";
  }
}