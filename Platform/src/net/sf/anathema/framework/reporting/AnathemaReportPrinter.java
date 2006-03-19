package net.sf.anathema.framework.reporting;

import java.io.InputStream;
import java.io.OutputStream;

import net.sf.anathema.framework.reporting.jasper.datasource.ReportDataSourceAdapter;
import net.sf.anathema.framework.repository.IItem;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRReport;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class AnathemaReportPrinter {

  public void compileReport(IItem item, IReport report, OutputStream outputStream) throws ReportException {
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

  public JRReport createSubReport(String filePath) throws JRException {
    InputStream inputStream = AnathemaReportPrinter.class.getClassLoader().getResourceAsStream(filePath);
    return (JasperReport) JRLoader.loadObject(inputStream);
  }
}