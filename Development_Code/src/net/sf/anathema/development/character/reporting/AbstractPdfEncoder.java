package net.sf.anathema.development.character.reporting;

import com.lowagie.text.pdf.PdfContentByte;

public class AbstractPdfEncoder {

  protected final void setFillColorWhite(PdfContentByte directContent) {
    directContent.setRGBColorFill(255, 255, 255);
  }

  protected final void setFillColorBlack(PdfContentByte directContent) {
    directContent.setRGBColorFill(0, 0, 0);
  }
}