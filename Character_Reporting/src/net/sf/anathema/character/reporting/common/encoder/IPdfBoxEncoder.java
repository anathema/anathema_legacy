package net.sf.anathema.character.reporting.common.encoder;

import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.reporting.common.Bounds;

public interface IPdfBoxEncoder {
  public static final int HEADER_HEIGHT = 12;
  public static final int ARCSPACE = HEADER_HEIGHT / 2;
  public static final int ARC_SIZE = 2 * ARCSPACE;

  public void encodeContentBox(PdfContentByte directContent, Bounds bounds);
}
