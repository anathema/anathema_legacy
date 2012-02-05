package net.sf.anathema.framework.reporting.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import net.disy.commons.core.text.font.FontStyle;
import net.sf.anathema.framework.styledtext.model.ITextFormat;

public class PdfReportUtils {

  public Paragraph createNewParagraph(String text, int alignment, int style) {
    Font font = createDefaultFont(10, style);
    Paragraph paragraph = new Paragraph(text, font);
    paragraph.setAlignment(alignment);
    paragraph.setLeading(font.getSize() * 1.2f);
    return paragraph;
  }

  public Font createDefaultFont(float size, int style) {
    return FontFactory.getFont(FontFactory.TIMES, size, style, BaseColor.BLACK);
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
}
