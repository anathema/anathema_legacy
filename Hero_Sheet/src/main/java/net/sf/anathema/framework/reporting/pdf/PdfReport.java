package net.sf.anathema.framework.reporting.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.framework.reporting.ReportException;
import net.sf.anathema.character.main.framework.item.Item;

public interface PdfReport extends Report {

  void performPrint(Item item, Document document, PdfWriter writer) throws ReportException;

}
