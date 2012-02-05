package net.sf.anathema.framework.reporting;

import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import net.disy.commons.core.text.font.FontStyle;
import net.sf.anathema.framework.styledtext.model.ITextFormat;

import java.awt.*;

public class ITextReportUtils {

  public Paragraph createNewParagraph(String text, int alignment, int style) {
    Font font = createDefaultFont(10, style);
    Paragraph paragraph = new Paragraph(text, font);
    paragraph.setAlignment(alignment);
    paragraph.setLeading(font.getSize() * 1.2f);
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
}
