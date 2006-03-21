package net.sf.anathema.development.reporting.encoder.voidstate.subreports.ability;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.generic.framework.reporting.IAbilityReportConstants;
import net.sf.anathema.character.generic.framework.reporting.datasource.AbilitySetDataSource;
import net.sf.anathema.development.reporting.encoder.AbstractCharacterSheetPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.columns.IOneColumnEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.format.IVoidStateFormatConstants;
import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;
import net.sf.anathema.development.reporting.util.ParameterUtilities;

import org.dom4j.Element;

public class VoidstateAbilitySetPageEncoder extends AbstractCharacterSheetPageEncoder implements
    IVoidStateFormatConstants {

  private final VoidstateBasicsEncoder encoder;

  public VoidstateAbilitySetPageEncoder(VoidstateBasicsEncoder basicsEncoder) {
    this.encoder = basicsEncoder;
  }

  public int encodeBand(Element bandElement) {
    Rectangle boxRectangle = calculateExtents(encoder);
    Rectangle abilityRect = VoidstateAbilityGroupPageEncoder.calculateExtents(encoder, 5);
    abilityRect.x = boxRectangle.x;
    int abilityHeight = 0;
    for (int currentGroup = 0; currentGroup < 5; currentGroup++) {
      abilityRect.y = abilityHeight;
      String subreportParameterName = IAbilityReportConstants.SUBREPORT_ABILITY_GROUP;
      Map<String, String> subreportParameterMap = new HashMap<String, String>();
      String nameParameter = getDatasourceParameterValue(currentGroup, AbilitySetDataSource.COLUMN_NAME);
      subreportParameterMap.put(nameParameter, IAbilityReportConstants.PARAM_GROUP_NAME);
      String dataSourceParameter = getDatasourceParameterValue(
          currentGroup,
          AbilitySetDataSource.COLUMN_GROUP_DATASOURCE);
      subreportParameterMap.put(dataSourceParameter, IAbilityReportConstants.PARAM_ABILITYGROUP_DATASOURCE);
      encodeSubreportWithExpressions(bandElement, abilityRect, subreportParameterName, null, subreportParameterMap);
      abilityHeight += abilityRect.height + TEXT_PADDING;
    }
    return abilityHeight;
  }

  private String getDatasourceParameterValue(int currentRow, String columnName) {
    return ParameterUtilities.parameterString(IAbilityReportConstants.PARAM_ABILITYSET_DATASOURCE)
        + methodCall("getValue", new Object[] { currentRow, quotify(columnName) });
  }

  public static Rectangle calculateExtents(IOneColumnEncoder basicsEncoder) {
    return basicsEncoder.createOneColumnBoxBoundsWithTitle(17, 15, new Point(0, 0));
  }

  public String getGroupName() {
    return "VoidstateAbilitySetSubreport";
  }
}