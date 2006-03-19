package net.sf.anathema.framework.reporting;

public interface IReportDataSource {

  public int getRowCount();

  public Object getValue(int currentRow, String columnName);

}
