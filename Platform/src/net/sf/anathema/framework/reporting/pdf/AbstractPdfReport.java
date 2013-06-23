package net.sf.anathema.framework.reporting.pdf;

import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.framework.reporting.ReportException;
import net.sf.anathema.framework.repository.Item;

import java.io.OutputStream;

public abstract class AbstractPdfReport implements Report, PdfReport {

  @Override
  public final void print(Item item, OutputStream stream) throws ReportException {
    PdfReportPrinter printer = new PdfReportPrinter();
    printer.printReport(item, this, stream);
  }
}
