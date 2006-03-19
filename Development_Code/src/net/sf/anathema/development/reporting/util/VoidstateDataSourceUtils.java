package net.sf.anathema.development.reporting.util;

import net.sf.anathema.character.generic.framework.reporting.datasource.CharmDataSource;

public class VoidstateDataSourceUtils {

  public static DataSourceColumn[] getMagicDataSourceColumns() {
    return new DataSourceColumn[] {
        new DataSourceColumn(CharmDataSource.COLUMN_PRINT_NAME, 0.3, "Name"),
        new DataSourceColumn(CharmDataSource.COLUMN_COST, 0.3, "Cost"),
        new DataSourceColumn(CharmDataSource.COLUMN_DURATION, 0.15, "Duration"),
        new DataSourceColumn(CharmDataSource.COLUMN_TYPE, 0.1, "Type"),
        new DataSourceColumn(CharmDataSource.COLUMN_SOURCE, 0.15, "Source") };
  }

  public static int calculatePrintWidth(int width, int spacing, int columnCount) {
    return (width - spacing * (columnCount - 1));
  }
}