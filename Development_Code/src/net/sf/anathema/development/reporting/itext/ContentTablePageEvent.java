package net.sf.anathema.development.reporting.itext;

import java.util.Map;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;

public class ContentTablePageEvent extends PdfPageEventHelper {

  private int pageOffset = 0;
  private final Map map;

  public ContentTablePageEvent(Map map) {
    this.map = map;
  }

  @Override
  public void onGenericTag(PdfWriter writer, Document document, Rectangle rect, String text) {
    Object obj[] = (Object[]) map.get(text);
    obj[1] = rect;
    obj[2] = String.valueOf(writer.getPageNumber());
  }

  @Override
  public void onEndPage(PdfWriter writer, Document document) {
    try {
      int n = pageOffset + writer.getPageNumber();
      String ns = String.valueOf(n);
      ColumnText.showTextAligned(
          writer.getDirectContent(),
          Element.ALIGN_CENTER,
          new Phrase(ns),
          (document.right() + document.left()) / 2,
          document.bottom() - 20,
          0);
    }
    catch (Exception e) {
      throw new ExceptionConverter(e);
    }
  }

  public void setPageOffset(int newOffset) {
    this.pageOffset = newOffset;
  }
}