package net.sf.anathema.development.reporting.encoder.voidstate.subreports.description;

import java.util.Map;

import net.sf.anathema.character.generic.framework.reporting.template.voidstate.ExaltVoidstateReportTemplate;
import net.sf.anathema.development.reporting.encoder.AbstractPagedCharacterSheetEncoder;
import net.sf.anathema.development.reporting.encoder.ICharacterSheetPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.util.SubreportUtilities;
import net.sf.anathema.development.reporting.util.TraitEncoder;
import net.sf.anathema.initialization.InitializationException;

public class VoidstateSiderealDescriptionSubreportEncoder extends AbstractPagedCharacterSheetEncoder {

  private final VoidstateBasicsEncoder basicsEncoder;
  private final TraitEncoder traitEncoder;

  public VoidstateSiderealDescriptionSubreportEncoder(VoidstateBasicsEncoder basicsEncoder, TraitEncoder traitEncoder)
      throws InitializationException {
    super(SubreportUtilities.createPageFormat(AbstractVoidstateDescriptionPageEncoder.calculateExtents(basicsEncoder)));
    this.basicsEncoder = basicsEncoder;
    this.traitEncoder = traitEncoder;
  }

  @Override
  protected ICharacterSheetPageEncoder[] getPageEncoders() {
    return new ICharacterSheetPageEncoder[] { new VoidstateSiderealDescriptionPageEncoder(basicsEncoder, traitEncoder) };
  }

  @Override
  protected void addParameterClasses(Map<String, String> parameterClasses) {
    ExaltVoidstateReportTemplate.addSiderealParameterClasses(parameterClasses);
  }

  @Override
  protected String getReportName() {
    return "VoidstateSiderealDescriptionSubreport";
  }
}