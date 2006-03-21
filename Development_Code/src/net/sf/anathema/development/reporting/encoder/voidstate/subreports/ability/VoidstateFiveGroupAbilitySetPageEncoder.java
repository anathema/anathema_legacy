package net.sf.anathema.development.reporting.encoder.voidstate.subreports.ability;

import java.awt.Rectangle;
import java.util.Map;

import net.sf.anathema.character.generic.framework.reporting.IAbilityReportConstants;
import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.util.VoidStateBoxEncoder;

import org.dom4j.Element;

public class VoidstateFiveGroupAbilitySetPageEncoder extends AbstractVoidstateAbilitySetPageEncoder {

  private final VoidstateBasicsEncoder encoder;

  public VoidstateFiveGroupAbilitySetPageEncoder(VoidstateBasicsEncoder basicsEncoder) {
    this.encoder = basicsEncoder;
  }

  public int encodeBand(Element bandElement) {
    Rectangle boxRectangle = calculateExtents(encoder);
    Rectangle abilityRect = AbstractVoidstateAbilityGroupPageEncoder.calculateExtents(encoder, 5);
    abilityRect.x = boxRectangle.x + VoidStateBoxEncoder.TEXT_INSET;
    String subreportParameterName = IAbilityReportConstants.SUBREPORT_FIVE_ABILITY_GROUP;
    int abilityHeight = 0;
    for (int currentGroup = 0; currentGroup < 5; currentGroup++) {
      abilityRect.y = abilityHeight;
      Map<String, String> subreportParameterMap = createSubreportParameterMap(currentGroup);
      encodeSubreportWithExpressions(bandElement, abilityRect, subreportParameterName, null, subreportParameterMap);
      abilityHeight += abilityRect.height + TEXT_PADDING;
    }
    return abilityHeight;
  }

  public String getGroupName() {
    return "VoidstateFiveGroupAbilitySetSubreport";
  }
}