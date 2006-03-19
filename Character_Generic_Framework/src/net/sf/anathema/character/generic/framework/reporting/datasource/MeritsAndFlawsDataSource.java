package net.sf.anathema.character.generic.framework.reporting.datasource;

import net.sf.anathema.framework.reporting.IReportDataSource;

public class MeritsAndFlawsDataSource implements IReportDataSource {

  public static final String COLUMN_PRINT_NAME = "printName"; //$NON-NLS-1$
  public static final String COLUMN_TEXT = "text"; //$NON-NLS-1$

  public int getRowCount() {
    return 0;
  }

  public Object getValue(int currentRow, String columnName) {
    return ""; //$NON-NLS-1$
  }
}