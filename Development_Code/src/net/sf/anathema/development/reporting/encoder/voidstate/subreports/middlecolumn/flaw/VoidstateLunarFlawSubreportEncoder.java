package net.sf.anathema.development.reporting.encoder.voidstate.subreports.middlecolumn.flaw;

import java.util.Map;

import net.sf.anathema.character.generic.framework.reporting.template.voidstate.ExaltVoidstateReportTemplate;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.development.reporting.encoder.AbstractPagedCharacterSheetEncoder;
import net.sf.anathema.development.reporting.encoder.ICharacterSheetPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.util.SubreportUtilities;
import net.sf.anathema.initialization.InitializationException;

public class VoidstateLunarFlawSubreportEncoder extends AbstractPagedCharacterSheetEncoder {

  private VoidstateBasicsEncoder columnEncoder;

  public VoidstateLunarFlawSubreportEncoder(VoidstateBasicsEncoder columnEncoder) throws InitializationException {
    super(SubreportUtilities.createPageFormat(AbstractVoidstateFlawPageEncoder.calculateBounds(columnEncoder)));
    this.columnEncoder = columnEncoder;
  }

  @Override
  protected ICharacterSheetPageEncoder[] getPageEncoders() {
    return new ICharacterSheetPageEncoder[] { new VoidstateLunarFlawPageEncoder(columnEncoder) };
  }

  @Override
  protected void addParameterClasses(Map<String, String> parameterClasses) {
    ExaltVoidstateReportTemplate.addVirtueFlawParameterClass(parameterClasses);
  }

  @Override
  protected String getReportName() {
    return "VoidState" + CharacterType.LUNAR.name() + "FlawSubreport";
  }
}