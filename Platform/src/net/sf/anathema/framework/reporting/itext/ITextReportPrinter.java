package net.sf.anathema.framework.reporting.itext;

import java.io.OutputStream;

import net.sf.anathema.framework.reporting.ReportException;
import net.sf.anathema.framework.repository.IItem;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfWriter;

public class ITextReportPrinter {

  public void printReport(IItem item, IITextReport report, OutputStream outputStream) throws ReportException {
    Document document = new Document();
    try {
      PdfWriter.getInstance(document, outputStream);
      document.open();
      report.performPrint(item, document);
    }
    catch (DocumentException de) {
      throw new ReportException(de);
    }
    document.close();
  }
}