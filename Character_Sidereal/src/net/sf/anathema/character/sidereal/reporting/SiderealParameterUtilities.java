package net.sf.anathema.character.sidereal.reporting;

import java.util.Map;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.framework.reporting.template.voidstate.ExaltVoidstateReportTemplate;
import net.sf.anathema.lib.resources.IResources;

public class SiderealParameterUtilities {

  public static void fillInSiderealParameters(
      Map<Object, Object> parameters,
      IGenericCharacter character,
      IResources resources) {
    parameters.put(ExaltVoidstateReportTemplate.RESPLENDENT_DESTINY_DATA_SOURCE, new ResplendentDestinyDataSource());
    parameters.put(ExaltVoidstateReportTemplate.COLLEGES_DATA_SOURCE, new SiderealCollegesDataSource(
        character,
        resources));
  }
}