package net.sf.anathema.development.reporting.encoder.voidstate.subreports.ability;

import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.generic.framework.reporting.IAbilityReportConstants;
import net.sf.anathema.character.generic.framework.reporting.datasource.AbilityGroupDataSource;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.development.reporting.encoder.AbstractCharacterSheetPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.util.VoidStateBoxEncoder;
import net.sf.anathema.development.reporting.util.ParameterUtilities;
import net.sf.anathema.framework.reporting.encoding.TextEncoding;

import org.dom4j.Element;

public abstract class AbstractVoidstateAbilityGroupPageEncoder extends AbstractCharacterSheetPageEncoder implements
    IVoidStateFormatConstants {

  private final VoidstateBasicsEncoder encoder;
  private final int abilityCount;

  public AbstractVoidstateAbilityGroupPageEncoder(VoidstateBasicsEncoder basicsEncoder, int abilityCount) {
    this.encoder = basicsEncoder;
    this.abilityCount = abilityCount;
  }

  public int encodeBand(Element bandElement) {
    int groupHeight = addAbilitySubreports(bandElement);
    Element textField = TextEncoding.addTextFieldElement(bandElement);
    addReportElement(textField, 0, 0, LINE_HEIGHT, groupHeight);
    TextEncoding.addVerticalTextElement(textField, FONT_SIZE, VALUE_CENTER);
    TextEncoding.addTextFieldExpression(
        textField,
        ParameterUtilities.parameterString(IAbilityReportConstants.PARAM_GROUP_NAME));
    return groupHeight;
  }

  private int addAbilitySubreports(Element bandElement) {
    Rectangle abilityRect = VoidstateSingleAbilityPageEncoder.calculateExtents(encoder);
    abilityRect.x = LINE_HEIGHT;
    String subreportParameterName = IAbilityReportConstants.SUBREPORT_SINGLE_ABILITY;
    int groupHeight = 0;
    for (int currentRow = 0; currentRow < abilityCount; currentRow++) {
      Map<String, String> subreportParameterMap = new HashMap<String, String>();
      String crossParameter = getDatasourceParameterValue(currentRow, AbilityGroupDataSource.COLUMN_CROSS);
      subreportParameterMap.put(crossParameter, IAbilityReportConstants.PARAM_PRINT_CROSS);
      String favoredParameter = getDatasourceParameterValue(currentRow, AbilityGroupDataSource.COLUMN_FAVORED);
      subreportParameterMap.put(favoredParameter, IAbilityReportConstants.PARAM_FILL_RECTANGLE);
      String nameParameter = getDatasourceParameterValue(currentRow, AbilityGroupDataSource.COLUMN_NAME);
      subreportParameterMap.put(nameParameter, IAbilityReportConstants.PARAM_TRAIT_NAME);
      String valueParameter = getDatasourceParameterValue(currentRow, AbilityGroupDataSource.COLUMN_VALUE);
      subreportParameterMap.put(valueParameter, IAbilityReportConstants.PARAM_TRAIT_VALUE);
      encodeSubreportWithExpressions(bandElement, abilityRect, subreportParameterName, null, subreportParameterMap);
      int singleHeight = abilityRect.height;
      groupHeight += singleHeight;
      abilityRect.y += singleHeight;
    }
    return groupHeight;
  }

  private String getDatasourceParameterValue(int currentRow, String columnName) {
    return ParameterUtilities.parameterString(IAbilityReportConstants.PARAM_ABILITYGROUP_DATASOURCE)
        + methodCall("getValue", new Object[] { currentRow, quotify(columnName) });
  }

  protected static Rectangle calculateExtents(VoidstateBasicsEncoder basicsEncoder, int abilityCount) {
    return new Rectangle(basicsEncoder.getSingleColumnWidth() - 2 * VoidStateBoxEncoder.TEXT_INSET, LINE_HEIGHT
        * abilityCount);
  }
}