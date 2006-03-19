package net.sf.anathema.character.solar.reporting;

import java.util.Map;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.framework.reporting.template.voidstate.ExaltVoidstateReportTemplate;
import net.sf.anathema.character.solar.virtueflaw.SolarVirtueFlawTemplate;
import net.sf.anathema.character.solar.virtueflaw.model.ISolarVirtueFlaw;
import net.sf.anathema.character.solar.virtueflaw.presenter.ISolarVirtueFlawModel;

public class SolarParameterUtilities {

  public static void fillInVirtueFlaw(IGenericCharacter character, Map<Object, Object> parameters) {
    ISolarVirtueFlaw virtueFlaw = ((ISolarVirtueFlawModel) character.getAdditionalModel(SolarVirtueFlawTemplate.ID)).getVirtueFlaw();
    parameters.put(ExaltVoidstateReportTemplate.VIRTUE_FLAW, virtueFlaw.getName().getText());
    parameters.put(ExaltVoidstateReportTemplate.VIRTUE_FLAW_CONDITION, virtueFlaw.getLimitBreak().getText());
  }
}