package net.sf.anathema.framework.reporting;

import net.sf.anathema.framework.repository.IItem;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;

public interface IITextReport extends IReport {

  public void performPrint(IItem item, Document document, PdfWriter writer) throws ReportException;

}