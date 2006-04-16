package net.sf.anathema.framework.reporting.jasper;

import net.sf.anathema.framework.reporting.IReportDataSource;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class ReportDataSourceAdapter implements JRDataSource {

  private int currentRow = -1;
  private final IReportDataSource dataSource;

  public ReportDataSourceAdapter(IReportDataSource dataSource) {
    this.dataSource = dataSource;
  }

  public ReportDataSourceAdapter(IReportDataSource dataSource, int startingRow) {
    this.dataSource = dataSource;
    currentRow = startingRow;
  }

  public boolean next() throws JRException {
    return currentRow++ < dataSource.getRowCount();
  }

  public Object getFieldValue(JRField jrField) throws JRException {
    return dataSource.getValue(currentRow, jrField.getName());
  }
}