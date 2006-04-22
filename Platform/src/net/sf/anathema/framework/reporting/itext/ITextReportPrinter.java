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
      PdfWriter writer = PdfWriter.getInstance(document, outputStream);
      writer.setPdfVersion(PdfWriter.VERSION_1_5);
      writer.setViewerPreferences(PdfWriter.DisplayDocTitle);
      document.addTitle(item.getDisplayName());
      document.addCreator("Anathema"); //$NON-NLS-1$
      document.open();
      report.performPrint(item, document, writer);
    }
    catch (DocumentException de) {
      throw new ReportException(de);
    }
    document.close();
  }
}