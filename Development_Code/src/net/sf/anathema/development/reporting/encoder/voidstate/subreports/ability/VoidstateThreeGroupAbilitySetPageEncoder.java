package net.sf.anathema.development.reporting.encoder.voidstate.subreports.ability;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Map;

import net.sf.anathema.character.generic.framework.reporting.IAbilityReportConstants;
import net.sf.anathema.development.reporting.encoder.voidstate.columns.IOneColumnEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.util.VoidStateBoxEncoder;

import org.dom4j.Element;

public class VoidstateThreeGroupAbilitySetPageEncoder extends AbstractVoidstateAbilitySetPageEncoder {

  private final VoidstateBasicsEncoder encoder;

  public VoidstateThreeGroupAbilitySetPageEncoder(VoidstateBasicsEncoder basicsEncoder) {
    this.encoder = basicsEncoder;
  }

  public int encodeBand(Element bandElement) {
    Rectangle boxRectangle = calculateExtents(encoder);
    Rectangle fiveAbilityRect = AbstractVoidstateAbilityGroupPageEncoder.calculateExtents(encoder, 5);
    Rectangle tenAbilityRect = AbstractVoidstateAbilityGroupPageEncoder.calculateExtents(encoder, 10);
    int xCoordinate = boxRectangle.x + VoidStateBoxEncoder.TEXT_INSET;
    fiveAbilityRect.x = xCoordinate;
    tenAbilityRect.x = xCoordinate;
    int abilityHeight = 0;
    for (int currentGroup = 0; currentGroup < 2; currentGroup++) {
      tenAbilityRect.y = abilityHeight;
      String subreportParameterName = IAbilityReportConstants.SUBREPORT_TEN_ABILITY_GROUP;
      Map<String, String> subreportParameterMap = createSubreportParameterMap(currentGroup);
      encodeSubreportWithExpressions(bandElement, tenAbilityRect, subreportParameterName, null, subreportParameterMap);
      abilityHeight += tenAbilityRect.height + TEXT_PADDING;
    }
    fiveAbilityRect.y = abilityHeight;
    String subreportParameterName = IAbilityReportConstants.SUBREPORT_FIVE_ABILITY_GROUP;
    Map<String, String> subreportParameterMap = createSubreportParameterMap(2);
    encodeSubreportWithExpressions(bandElement, fiveAbilityRect, subreportParameterName, null, subreportParameterMap);
    abilityHeight += fiveAbilityRect.height + TEXT_PADDING;
    return abilityHeight;
  }

  public static Rectangle calculateExtents(IOneColumnEncoder basicsEncoder) {
    return basicsEncoder.createOneColumnBoxBoundsWithTitle(17, 15, new Point(0, 0));
  }

  public String getGroupName() {
    return "VoidstateThreeGroupAbilitySetSubreport";
  }
}