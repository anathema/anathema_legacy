package net.sf.anathema.character.reporting.sheet.util;

import com.lowagie.text.pdf.PdfContentByte;
import static net.sf.anathema.character.reporting.sheet.util.IPdfBoxEncoder.ARC_SIZE;

public class BoxEncodingUtils {

  public static void add90DegreeArc(PdfContentByte directContent, float minX, float minY, float startAngle) {
    directContent.arc(minX, minY, minX + ARC_SIZE, minY + ARC_SIZE, startAngle, 90);
  }
}