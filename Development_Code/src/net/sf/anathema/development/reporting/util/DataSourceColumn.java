package net.sf.anathema.development.reporting.util;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.framework.reporting.jasper.IJasperXmlConstants;

public class DataSourceColumn {

  private final double widthShare;
  private final String columnName;
  private final String header;
  private final String justification;

  public DataSourceColumn(String columnName, double widthShare, String header, String justification) {
    Ensure.ensureTrue("Share must not exceed 1 or underrun 0", 0 <= widthShare && 1 >= widthShare);
    Ensure.ensureNotNull("Columnname must not be null", columnName);
    this.columnName = columnName;
    this.widthShare = widthShare;
    this.header = header;
    this.justification = justification;
  }

  public DataSourceColumn(String columnName, double widthShare, String header) {
    this(columnName, widthShare, header, IJasperXmlConstants.VALUE_LEFT);
  }

  public String getColumnName() {
    return columnName;
  }

  public double getWidthShare() {
    return widthShare;
  }

  public String getHeader() {
    return header;
  }

  public String getJustification() {
    return justification;
  }
}