package net.sf.anathema.development.reporting.encoder.voidstate.subreports.ability;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.generic.framework.reporting.IAbilityReportConstants;
import net.sf.anathema.character.generic.framework.reporting.datasource.AbilitySetDataSource;
import net.sf.anathema.character.reporting.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.development.reporting.encoder.AbstractCharacterSheetPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.columns.IOneColumnEncoder;
import net.sf.anathema.development.reporting.util.ParameterUtilities;

public abstract class AbstractVoidstateAbilitySetPageEncoder extends AbstractCharacterSheetPageEncoder implements
    IVoidStateFormatConstants {

  protected final String getDatasourceParameterValue(int currentRow, String columnName) {
    return ParameterUtilities.parameterString(IAbilityReportConstants.PARAM_ABILITYSET_DATASOURCE)
        + methodCall("getValue", new Object[] { currentRow, quotify(columnName) });
  }

  protected final Map<String, String> createSubreportParameterMap(int currentGroup) {
    Map<String, String> subreportParameterMap = new HashMap<String, String>();
    String nameParameter = getDatasourceParameterValue(currentGroup, AbilitySetDataSource.COLUMN_NAME);
    subreportParameterMap.put(nameParameter, IAbilityReportConstants.PARAM_GROUP_NAME);
    String dataSourceParameter = getDatasourceParameterValue(currentGroup, AbilitySetDataSource.COLUMN_GROUP_DATASOURCE);
    subreportParameterMap.put(dataSourceParameter, IAbilityReportConstants.PARAM_ABILITYGROUP_DATASOURCE);
    return subreportParameterMap;
  }

  public static Rectangle calculateExtents(IOneColumnEncoder basicsEncoder) {
    return basicsEncoder.createOneColumnBoxBoundsWithTitle(17, 15, new Point(0, 0));
  }
}
