package net.sf.anathema.development.reporting.encoder.voidstate.subreports.attribute;

import java.util.Map;

import net.sf.anathema.character.generic.framework.reporting.parameters.CharacterParameterUtilities;
import net.sf.anathema.development.reporting.encoder.AbstractPagedCharacterSheetEncoder;
import net.sf.anathema.development.reporting.encoder.ICharacterSheetPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.util.SubreportUtilities;
import net.sf.anathema.development.reporting.util.AttributesEncoder;
import net.sf.anathema.development.reporting.util.TraitEncoder;
import net.sf.anathema.initialization.InitializationException;

public class VoidstateDefaultAttributeSubreportEncoder extends AbstractPagedCharacterSheetEncoder {

  private final VoidstateBasicsEncoder basicsEncoder;
  private final TraitEncoder traitEncoder;

  public VoidstateDefaultAttributeSubreportEncoder(VoidstateBasicsEncoder basicsEncoder, TraitEncoder traitEncoder)
      throws InitializationException {
    super(SubreportUtilities.createPageFormat(AbstractVoidstateAttributePageEncoder.calculateExtents(basicsEncoder)));
    this.basicsEncoder = basicsEncoder;
    this.traitEncoder = traitEncoder;
  }

  @Override
  protected ICharacterSheetPageEncoder[] getPageEncoders() {
    return new ICharacterSheetPageEncoder[] { new VoidstateDefaultAttributePageEncoder(
        basicsEncoder,
        new AttributesEncoder(traitEncoder)) };
  }

  @Override
  protected void addParameterClasses(Map<String, String> parameterClasses) {
    CharacterParameterUtilities.addAttributeParameterClasses(parameterClasses);
  }

  @Override
  protected String getReportName() {
    return "VoidstateDefaultAttributeSubreport";
  }
}