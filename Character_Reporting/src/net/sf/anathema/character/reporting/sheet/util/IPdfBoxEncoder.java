package net.sf.anathema.character.reporting.sheet.util;

import net.sf.anathema.character.reporting.util.Bounds;

import com.lowagie.text.pdf.PdfContentByte;

public interface IPdfBoxEncoder {
  public static final float HEADER_HEIGHT = 12;
  public static final float ARCSPACE = HEADER_HEIGHT / 2;
  public static final float ARC_SIZE = 2 * ARCSPACE;

  public void encodeContentBox(PdfContentByte directContent, Bounds bounds);
}