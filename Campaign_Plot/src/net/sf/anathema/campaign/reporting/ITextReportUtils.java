package net.sf.anathema.campaign.reporting;

import java.awt.Color;

import net.disy.commons.core.text.font.FontStyle;
import net.sf.anathema.framework.styledtext.model.ITextFormat;

import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfAction;
import com.lowagie.text.pdf.PdfContentByte;

public class ITextReportUtils {

  public Paragraph createNewParagraph(String text, int alignment, int style) {
    Font font = createDefaultFont(10, style);
    Paragraph paragraph = new Paragraph(text, font);
    paragraph.setAlignment(alignment);
    paragraph.setLeading(font.size() * 1.2f);
    return paragraph;
  }

  public Font createDefaultFont(int size, int style) {
    return FontFactory.getFont(FontFactory.TIMES, size, style, Color.BLACK);
  }

  public int getStyle(ITextFormat format) {
    if (!format.isStyled()) {
      return Font.NORMAL;
    }
    int style = Font.NORMAL;
    if (format.isUnderline()) {
      style = style | Font.UNDERLINE;
    }
    FontStyle fontStyle = format.getFontStyle();
    if (fontStyle.isBold()) {
      style = style | Font.BOLD;
    }
    if (fontStyle.isItalic()) {
      style = style | Font.ITALIC;
    }
    return style;
  }

  public void textLine(
      PdfContentByte cb,
      float y,
      float left,
      float right,
      String connect,
      Font font,
      String leftString,
      String rightString,
      PdfAction link) {
    Phrase phraseLeft = new Phrase(leftString, font);
    Phrase phraseRight = new Phrase(rightString, font);
    float phraseLeftWidth = ColumnText.getWidth(phraseLeft);
    float phraseRightWidth = ColumnText.getWidth(phraseRight);
    float widthRemaining = right - left - phraseRightWidth - phraseLeftWidth;
    ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, phraseLeft, left, y, 0);
    if (connect != null) {
      float cw = ColumnText.getWidth(new Phrase(connect, font));
      int rep = (int) (widthRemaining / cw);
      if (rep > 0) {
        StringBuffer sb = new StringBuffer();
        for (int k = 0; k < rep; ++k)
          sb.append(connect);
        ColumnText.showTextAligned(
            cb,
            Element.ALIGN_RIGHT,
            new Phrase(sb.toString(), font),
            right - phraseRightWidth,
            y,
            0);
      }
    }
    ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, phraseRight, right, y, 0);
    if (link != null)
      cb.setAction(link, left, y, right, y + 12);
  }
}
