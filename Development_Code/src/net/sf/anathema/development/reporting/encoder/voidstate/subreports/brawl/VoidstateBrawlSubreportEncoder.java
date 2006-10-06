package net.sf.anathema.development.reporting.encoder.voidstate.subreports.brawl;

import java.util.Map;

import net.sf.anathema.character.generic.framework.reporting.template.AbstractMagicUserCharacterReportTemplate;
import net.sf.anathema.development.reporting.encoder.AbstractPagedCharacterSheetEncoder;
import net.sf.anathema.development.reporting.encoder.ICharacterSheetPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.util.SubreportUtilities;
import net.sf.anathema.framework.reporting.IReportDataSource;
import net.sf.anathema.initialization.InitializationException;

public class VoidstateBrawlSubreportEncoder extends AbstractPagedCharacterSheetEncoder {

  public VoidstateBrawlSubreportEncoder() throws InitializationException {
    super(SubreportUtilities.createPageFormat(VoidstateBrawlPageEncoder.calculateBounds()));
  }

  @Override
  protected ICharacterSheetPageEncoder[] getPageEncoders() {
    return new ICharacterSheetPageEncoder[] { new VoidstateBrawlPageEncoder() };
  }

  @Override
  protected void addParameterClasses(Map<String, String> parameterClasses) {
    parameterClasses.put(
        AbstractMagicUserCharacterReportTemplate.MELEE_WEAPON_DATA_SOURCE,
        IReportDataSource.class.getName());
  }

  @Override
  protected String getReportName() {
    return "VoidStateBrawlSubreport";
  }
}