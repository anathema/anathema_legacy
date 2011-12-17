package net.sf.anathema.character.reporting.sheet.util;

import java.awt.Color;

import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.util.Bounds;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;

public class PdfTextEncodingUtilities {

  public static Font createTextFont(BaseFont baseFont) {
    return createFont(baseFont, IVoidStateFormatConstants.FONT_SIZE);
  }

  public static Font createFont(BaseFont baseFont, float size) {
    return new Font(baseFont, size, Font.NORMAL, Color.black);
  }

  public static ColumnText encodeText(PdfContentByte directContent, Phrase phrase, Bounds bounds, float lineHeight)
      throws DocumentException {
    return encodeText(directContent, phrase, bounds, lineHeight, Element.ALIGN_LEFT);
  }

  public static ColumnText encodeText(
      PdfContentByte directContent,
      Phrase phrase,
      Bounds bounds,
      float lineHeight,
      int alignment) throws DocumentException {
    ColumnText columnText = new ColumnText(directContent);
    float minX = bounds.getMinX();
    float minY = bounds.getMinY();
    float maxX = bounds.getMaxX();
    float maxY = bounds.getMaxY();
    columnText.setSimpleColumn(phrase, minX, minY, maxX, maxY, lineHeight, alignment);
    columnText.go();
    return columnText;

  }
}