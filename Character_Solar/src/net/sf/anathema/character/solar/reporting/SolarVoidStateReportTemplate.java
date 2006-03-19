package net.sf.anathema.character.solar.reporting;

import java.util.Map;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.framework.reporting.template.voidstate.DefaultVoidstateSubreports;
import net.sf.anathema.character.generic.framework.reporting.template.voidstate.ExaltVoidstateReportTemplate;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.framework.reporting.ReportException;
import net.sf.anathema.lib.resources.IResources;

public class SolarVoidStateReportTemplate extends ExaltVoidstateReportTemplate {

  public SolarVoidStateReportTemplate(IResources resources) {
    super(CharacterType.SOLAR, resources, new DefaultVoidstateSubreports(CharacterType.SOLAR));
  }
  
  @Override
  protected void fillInCharacterTypeSpecificParameters(Map<Object, Object> parameters, IGenericCharacter character) throws ReportException {
    super.fillInCharacterTypeSpecificParameters(parameters, character);
    SolarParameterUtilities.fillInVirtueFlaw(character, parameters);
  }
}