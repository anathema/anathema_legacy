package net.sf.anathema.character.sidereal.reporting;

import java.util.Map;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.framework.reporting.template.voidstate.ExaltVoidstateReportTemplate;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.framework.reporting.ReportException;
import net.sf.anathema.lib.resources.IResources;

public class SiderealVoidStateReportTemplate extends ExaltVoidstateReportTemplate {

  public SiderealVoidStateReportTemplate(IResources resources) {
    super(CharacterType.SIDEREAL, resources, new SiderealVoidStateSubreports());
  }

  @Override
  protected void fillInCharacterTypeSpecificParameters(Map<Object, Object> parameters, IGenericCharacter character)
      throws ReportException {
    super.fillInCharacterTypeSpecificParameters(parameters, character);
    SiderealParameterUtilities.fillInSiderealParameters(parameters, character, getResources());
  }
}