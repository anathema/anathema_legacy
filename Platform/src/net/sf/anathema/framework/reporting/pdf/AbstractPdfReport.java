package net.sf.anathema.framework.reporting.pdf;

import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.framework.reporting.ReportException;
import net.sf.anathema.framework.repository.IItem;

import java.io.OutputStream;

public abstract class AbstractPdfReport implements Report, PdfReport {

  @Override
  public void print(IItem item, OutputStream stream) throws ReportException {
    PdfReportPrinter printer = new PdfReportPrinter();
    printer.printReport(item, this, stream);
  }
}
