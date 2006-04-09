package net.sf.anathema.development.reporting.encoder.voidstate.subreports.special;

import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.generic.framework.reporting.parameters.CombatParameterUtilities;
import net.sf.anathema.character.generic.framework.reporting.parameters.HealthParameterUtilities;
import net.sf.anathema.character.generic.framework.reporting.template.AbstractMagicUserCharacterReportTemplate;
import net.sf.anathema.character.generic.framework.reporting.template.voidstate.ExaltVoidstateReportTemplate;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.development.reporting.encoder.voidstate.pages.VoidstateFirstPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.subreports.attribute.AbstractVoidstateAttributePageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.subreports.combat.stats.VoidstateCombatStatsPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.subreports.middlecolumn.DefaultMiddleColumnPageEncoder;

import org.dom4j.Element;

public class VoidstateBeastformPageEncoder extends VoidstateFirstPageEncoder {

  public VoidstateBeastformPageEncoder(int pageWidth) {
    super(pageWidth);
  }

  @Override
  protected int encodeMiddleColumn(Element bandElement, int centerHeight) {
    Rectangle boxRectangle = DefaultMiddleColumnPageEncoder.getExtents(basicsEncoder);
    boxRectangle.setLocation(basicsEncoder.getSecondColumnX(), centerHeight);
    encodeSubreport(bandElement, boxRectangle, ExaltVoidstateReportTemplate.PARAM_LUNAR_MIDDLECOLUMN_SUBREPORT);
    return boxRectangle.height;
  }

  @Override
  protected int encodeAttributes(int y, Element bandElement) {
    Rectangle boxRectangle = AbstractVoidstateAttributePageEncoder.calculateExtents(basicsEncoder);
    boxRectangle.setLocation(basicsEncoder.getFirstColumnX(), y);
    Map<String, String> subreportParameterMap = new HashMap<String, String>();
    for (AttributeType attributeType : AttributeType.values()) {
      fillInBeastFormParameter(subreportParameterMap, attributeType.getId());
    }
    String subreportParameterName = ExaltVoidstateReportTemplate.PARAM_LUNAR_ATTRIBUTE_SUBREPORT;
    encodeSubreportWithParameters(bandElement, boxRectangle, subreportParameterName, null, subreportParameterMap);
    return boxRectangle.height;
  }

  @Override
  protected int encodeHealthData(Element bandElement, Rectangle boxRectangle, Rectangle textRect, int halfWidth) {
    Rectangle subreportRectangle = new Rectangle(textRect);
    subreportRectangle.width = halfWidth;
    Map<String, String> subreportParameterMap = new HashMap<String, String>();
    fillInBeastFormParameter(subreportParameterMap, HealthParameterUtilities.getSoakParameter(HealthType.Bashing));
    fillInBeastFormParameter(subreportParameterMap, HealthParameterUtilities.getSoakParameter(HealthType.Lethal));
    String subreportParameterName = ExaltVoidstateReportTemplate.PARAM_HEALTH_SUBREPORT;
    encodeSubreportWithParameters(bandElement, subreportRectangle, subreportParameterName, null, subreportParameterMap);
    return boxRectangle.height;
  }

  @Override
  protected int encodeCombatStats(int y, Element bandElement) {
    Rectangle boxRectangle = VoidstateCombatStatsPageEncoder.calculateExtents(basicsEncoder);
    boxRectangle.setLocation(basicsEncoder.getFirstColumnX(), y);
    Map<String, String> subreportParameterMap = new HashMap<String, String>();
    for (String parameter : CombatParameterUtilities.combatStatsParameterNames) {
      fillInBeastFormParameter(subreportParameterMap, parameter);
    }
    String subreportParameterName = ExaltVoidstateReportTemplate.PARAM_COMBAT_STATS_SUBREPORT;
    encodeSubreportWithParameters(bandElement, boxRectangle, subreportParameterName, null, subreportParameterMap);
    return boxRectangle.height;
  }

  @Override
  protected void addBrawlWeapons(Element parent, Rectangle textRect, int yOffset) {
    Rectangle bounds = new Rectangle(textRect.x, textRect.y + yOffset, textRect.width, textRect.height - yOffset);
    Map<String, String> subreportParameterMap = new HashMap<String, String>();
    subreportParameterMap.put(
        ExaltVoidstateReportTemplate.PARAM_BEASTFORM_BRAWL_DATA_SOURCE,
        AbstractMagicUserCharacterReportTemplate.MELEE_WEAPON_DATA_SOURCE);
    encodeSubreportWithParameters(
        parent,
        bounds,
        ExaltVoidstateReportTemplate.PARAM_BRAWL_SUBREPORT,
        null,
        subreportParameterMap);
  }

  private void fillInBeastFormParameter(Map<String, String> subreportParameterMap, String parameter) {
    subreportParameterMap.put(ExaltVoidstateReportTemplate.getBeastFormParameter(parameter), parameter);
  }

  @Override
  public String getGroupName() {
    return "VoidstateBeastformPage";
  }
}