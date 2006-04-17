package net.sf.anathema.development.reporting.itext;

import java.awt.Color;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfAction;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfDestination;
import com.lowagie.text.pdf.PdfOutline;
import com.lowagie.text.pdf.PdfPageLabels;
import com.lowagie.text.pdf.PdfWriter;

public class simple_toc {

  public final ArrayList list = new ArrayList();
  public final Map map = new HashMap();
  public int pageOffset;

  public static void textLine(
      PdfContentByte cb,
      float y,
      float left,
      float right,
      String connect,
      Font fontConnect,
      Phrase phraseLeft,
      Phrase phraseRight,
      PdfAction link) {
    float phraseLeftWidth = ColumnText.getWidth(phraseLeft);
    float phraseRightWidth = ColumnText.getWidth(phraseRight);
    float widthRemaining = right - left - phraseRightWidth - phraseLeftWidth;
    ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, phraseLeft, left, y, 0);
    if (connect != null) {
      float cw = ColumnText.getWidth(new Phrase(connect, fontConnect));
      int rep = (int) (widthRemaining / cw);
      if (rep > 0) {
        StringBuffer sb = new StringBuffer();
        for (int k = 0; k < rep; ++k)
          sb.append(connect);
        ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, new Phrase(sb.toString(), fontConnect), right
            - phraseRightWidth, y, 0);
      }
    }
    ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, phraseRight, right, y, 0);
    if (link != null)
      cb.setAction(link, left, y, right, y + 12);
  }

  public static void main(String[] args) {
    try {
      Document doc = new Document(PageSize.A4, 72, 72, 72, 72);
      PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("c:\\simple_toc.pdf"));
      simple_toc toc = new simple_toc();
      ContentTablePageEvent event = new ContentTablePageEvent(toc.map);
      writer.setPageEvent(event);
      String text = "Some text lines ";
      for (int k = 0; k < 7; ++k)
        text += text;
      doc.open();
      Font font = new Font(Font.TIMES_ROMAN, 16, Font.NORMAL, Color.red);
      writer.setLinearPageMode();
      for (int k = 1; k < 500; ++k) {
        String level = String.valueOf(k);
        String txt_level = "The clickable header level " + level + " goes here";
        Chunk ck = new Chunk(txt_level, font).setGenericTag(level);
        Object obj[] = new Object[3];
        obj[0] = ck;
        toc.list.add(level);
        toc.map.put(level, obj);
        doc.add(new Paragraph(ck));
        doc.add(new Paragraph(text));
      }
      doc.newPage();
      PdfContentByte cb = writer.getDirectContent();
      int split = writer.getPageNumber();
      PdfOutline rootOutline = cb.getRootOutline();
      PdfAction linkTOC = PdfAction.gotoLocalPage(split, new PdfDestination(PdfDestination.XYZ, 0, 10000, 0), writer);
      new PdfOutline(rootOutline, linkTOC, "Table Of Contents"); //$NON-NLS-1$
      event.setPageOffset(1 - split);
      float top = doc.top();
      float bottom = doc.bottom();
      float left = doc.left();
      float right = doc.right();
      ColumnText.showTextAligned(
          cb,
          Element.ALIGN_CENTER,
          new Phrase("Table Of Contents", new Font(Font.HELVETICA, 24)), //$NON-NLS-1$
          (left + right) / 2,
          top - 24,
          0);

      float offset = top - 48;
      for (int k = 0; k < toc.list.size(); ++k) {
        Object obj[] = (Object[]) toc.map.get(toc.list.get(k));
        Chunk ck = new Chunk(((Chunk) obj[0]).content());
        Rectangle rect = (Rectangle) obj[1];
        String number = (String) obj[2];
        PdfAction link = PdfAction.gotoLocalPage(Integer.parseInt(number), new PdfDestination(
            PdfDestination.XYZ,
            rect.left(),
            rect.top(),
            0), writer);
        PdfAction link2 = PdfAction.gotoLocalPage(Integer.parseInt(number), new PdfDestination(
            PdfDestination.XYZ,
            rect.left(),
            rect.top(),
            0), writer);
        new PdfOutline(rootOutline, link2, ck.content());
        textLine(cb, offset, left, right, " .", new Font(), new Phrase(ck), new Phrase(number), link); //$NON-NLS-1$
        offset -= 16;
        if (offset < bottom) {
          offset = top - 16;
          doc.newPage();
        }
      }

      doc.newPage();
      int totalPages = writer.getPageNumber() - 1;
      int reorder[] = new int[totalPages];
      for (int k = split; k <= totalPages; ++k)
        reorder[k - split] = k;
      int off = totalPages - split;
      for (int k = 1; k < split; ++k)
        reorder[off + k] = k;
      writer.reorderPages(reorder);
      PdfPageLabels pageLabels = new PdfPageLabels();
      pageLabels.addPageLabel(1, PdfPageLabels.LOWERCASE_ROMAN_NUMERALS);
      pageLabels.addPageLabel(totalPages - split + 2, PdfPageLabels.DECIMAL_ARABIC_NUMERALS);
      writer.setPageLabels(pageLabels);
      writer.setViewerPreferences(PdfWriter.PageModeUseOutlines);
      doc.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}