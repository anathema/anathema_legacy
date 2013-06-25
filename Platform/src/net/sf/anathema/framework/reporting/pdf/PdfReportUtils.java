package net.sf.anathema.framework.reporting.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;

public class PdfReportUtils {

  public Font createDefaultFont(float size, int style) {
    return FontFactory.getFont(FontFactory.TIMES, size, style, BaseColor.BLACK);
  }
}