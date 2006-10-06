package net.sf.anathema.development.reporting.encoder.voidstate.subreports.anima;

import java.util.Map;

import net.sf.anathema.character.generic.framework.reporting.ICharacterReportConstants;
import net.sf.anathema.character.generic.framework.reporting.parameters.AdvantageParameterUtilities;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.development.reporting.encoder.AbstractPagedCharacterSheetEncoder;
import net.sf.anathema.development.reporting.encoder.ICharacterSheetPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.columns.IOneColumnEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.util.SubreportUtilities;
import net.sf.anathema.initialization.InitializationException;

public class VoidstateDbAnimaSubreportEncoder extends AbstractPagedCharacterSheetEncoder {

  private IOneColumnEncoder columnEncoder;

  public VoidstateDbAnimaSubreportEncoder(IOneColumnEncoder columnEncoder) throws InitializationException {
    super(SubreportUtilities.createPageFormat(AbstractVoidstateAnimaPageEncoder.calculateBounds()));
    this.columnEncoder = columnEncoder;
  }

  @Override
  protected ICharacterSheetPageEncoder[] getPageEncoders() {
    return new ICharacterSheetPageEncoder[] { new VoidstateDbAnimaPageEncoder(columnEncoder) };
  }

  @Override
  protected void addParameterClasses(Map<String, String> parameterClasses) {
    AdvantageParameterUtilities.addEssenceParameterClass(parameterClasses);
    parameterClasses.put(ICharacterReportConstants.BREEDING_VALUE, Integer.class.getName());
  }

  @Override
  protected String getReportName() {
    return "VoidState" + CharacterType.DB.name() + "AnimaSubreport";
  }
}