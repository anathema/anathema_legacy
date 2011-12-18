package net.sf.anathema.character.reporting.pdf.rendering.page;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;

public interface IPdfPageEncoder {

  public void encode(Document document, PdfContentByte directContent, ReportContent content) throws DocumentException;
}
