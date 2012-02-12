package net.sf.anathema.framework.reporting.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.framework.reporting.ReportException;
import net.sf.anathema.framework.repository.IItem;

public interface PdfReport extends Report {

  public void performPrint(IItem item, Document document, PdfWriter writer) throws ReportException;

}
