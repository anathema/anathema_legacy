package net.sf.anathema.character.reporting.extended.util;

import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.reporting.util.Bounds;

public interface IPdfBoxEncoder {
  public static final float HEADER_HEIGHT = 12;
  public static final float ARCSPACE = HEADER_HEIGHT / 2;
  public static final float ARC_SIZE = 2 * ARCSPACE;

  public void encodeContentBox(PdfContentByte directContent, Bounds bounds);
}