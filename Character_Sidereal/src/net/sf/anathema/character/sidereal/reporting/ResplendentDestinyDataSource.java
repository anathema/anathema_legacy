package net.sf.anathema.character.sidereal.reporting;

import net.sf.anathema.framework.reporting.IReportDataSource;

public class ResplendentDestinyDataSource implements IReportDataSource {

  public static final String COLUMN_PRINT_NAME = "printName"; //$NON-NLS-1$
  public static final String COLUMN_COLLEGE = "college"; //$NON-NLS-1$
  public static final String COLUMN_EFFECT = "effect"; //$NON-NLS-1$
  public static final String COLUMN_DURATION = "duration"; //$NON-NLS-1$

  public int getRowCount() {
    return 0;
  }

  public Object getValue(int currentRow, String columnName) {
    return ""; //$NON-NLS-1$
  }
}