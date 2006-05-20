package net.sf.anathema.character.reporting.sheet.util;

import java.io.IOException;

import net.sf.anathema.character.reporting.util.Bounds;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;

public class PdfTextEncodingUtilities {

  public static float encodeText(PdfContentByte directContent, Phrase phrase, Bounds bounds, float lineHeight)
      throws DocumentException {
    ColumnText columnText = new ColumnText(directContent);
    float minX = bounds.getMinX();
    float minY = bounds.getMinY();
    float maxX = bounds.getMaxX();
    float maxY = bounds.getMaxY();
    columnText.setSimpleColumn(phrase, minX, minY, maxX, maxY, lineHeight, PdfContentByte.ALIGN_LEFT);
    columnText.go();
    return columnText.getYLine();
  }

  public static BaseFont createBaseFont(String fontName) throws DocumentException, IOException {
    return BaseFont.createFont(fontName, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
  }
}