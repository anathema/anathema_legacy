package net.sf.anathema.framework.reporting;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import net.sf.anathema.framework.repository.IItem;

public interface IITextReport extends IReport {

  public void performPrint(IItem item, Document document, PdfWriter writer) throws ReportException;

}
