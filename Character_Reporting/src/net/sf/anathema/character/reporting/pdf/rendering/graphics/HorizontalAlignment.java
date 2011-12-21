package net.sf.anathema.character.reporting.pdf.rendering.graphics;

import com.lowagie.text.Element;

public enum HorizontalAlignment {
  
  Left(Element.ALIGN_LEFT), Right(Element.ALIGN_RIGHT), Center(Element.ALIGN_CENTER), Justified(Element.ALIGN_JUSTIFIED);
  private int pdfAlignment;

  private HorizontalAlignment(int pdfAlignment) {
    this.pdfAlignment = pdfAlignment;
  }

  public int getPdfAlignment() {
    return pdfAlignment;
  }
}
