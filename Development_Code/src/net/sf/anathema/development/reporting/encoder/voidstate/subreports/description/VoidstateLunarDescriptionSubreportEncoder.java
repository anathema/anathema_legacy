package net.sf.anathema.development.reporting.encoder.voidstate.subreports.description;

import java.util.Map;

import net.sf.anathema.character.generic.framework.reporting.template.voidstate.ExaltVoidstateReportTemplate;
import net.sf.anathema.development.reporting.encoder.AbstractPagedCharacterSheetEncoder;
import net.sf.anathema.development.reporting.encoder.ICharacterSheetPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.util.SubreportUtilities;
import net.sf.anathema.initialization.InitializationException;

public class VoidstateLunarDescriptionSubreportEncoder extends AbstractPagedCharacterSheetEncoder {

  private final VoidstateBasicsEncoder basicsEncoder;

  public VoidstateLunarDescriptionSubreportEncoder(VoidstateBasicsEncoder basicsEncoder) throws InitializationException {
    super(SubreportUtilities.createPageFormat(AbstractVoidstateDescriptionPageEncoder.calculateExtents(basicsEncoder)));
    this.basicsEncoder = basicsEncoder;
  }

  @Override
  protected ICharacterSheetPageEncoder[] getPageEncoders() {
    return new ICharacterSheetPageEncoder[] { new VoidstateLunarDescriptionPageEncoder(basicsEncoder) };
  }

  @Override
  protected void addParameterClasses(Map<String, String> parameterClasses) {
    ExaltVoidstateReportTemplate.addLunarDescriptionParameterClasses(parameterClasses);
  }

  @Override
  protected String getReportName() {
    return "VoidstateLunarDescriptionSubreport";
  }
}