package net.sf.anathema.campaign.reporting;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

public class SeriesReportUtils {

  private final ITextReportUtils reportUtils = new ITextReportUtils();

  public void printPageNumber(PdfWriter writer, Document document, String pageNumber) {
    PdfContentByte directContent = writer.getDirectContent();
    PdfTemplate template = directContent.createTemplate(document.getPageSize().width(), document.getPageSize().height());
    template.moveTo(document.left(), document.bottom() - 11);
    template.lineTo(document.right(), document.bottom() - 11);
    template.stroke();
    directContent.addTemplate(template, 0, 0);
    ColumnText.showTextAligned(directContent, Element.ALIGN_CENTER, new Phrase(
        pageNumber,
        reportUtils.createDefaultFont(8, Font.NORMAL)), document.getPageSize().width() / 2, document.bottom() - 20, 0);
  }
}