package net.sf.anathema.framework.reporting.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import net.sf.anathema.framework.reporting.ReportException;
import net.sf.anathema.framework.repository.IItem;

import java.io.OutputStream;

public class PdfReportPrinter {

  public void printReport(IItem item, PdfReport report, OutputStream outputStream) throws ReportException {
    Document document = new Document();
    try {
      PdfWriter writer = PdfWriter.getInstance(document, outputStream);
      writer.setPdfVersion(PdfWriter.VERSION_1_5);
      writer.setViewerPreferences(PdfWriter.DisplayDocTitle);
      document.addTitle(item.getDisplayName());
      document.addCreator("Anathema"); //$NON-NLS-1$
      document.open();
      report.performPrint(item, document, writer);
    } catch (DocumentException de) {
      throw new ReportException(de);
    }
    document.close();
  }
}
