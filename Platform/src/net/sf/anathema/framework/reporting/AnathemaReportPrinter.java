package net.sf.anathema.framework.reporting;

import java.io.OutputStream;

import net.sf.anathema.framework.reporting.jasper.datasource.ReportDataSourceAdapter;
import net.sf.anathema.framework.repository.IItem;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class AnathemaReportPrinter {

  public void printReport(IItem item, IReport report, OutputStream outputStream) throws ReportException {
    try {
      JRDataSource jasperDataSource = new ReportDataSourceAdapter(report.getDataSource(item));
      JasperPrint print = JasperFillManager.fillReport(
          report.createReportInputStream(),
          report.getParameters(item),
          jasperDataSource);
      JasperExportManager.exportReportToPdfStream(print, outputStream);
    }
    catch (JRException e) {
      throw new ReportException(e);
    }
  }
}