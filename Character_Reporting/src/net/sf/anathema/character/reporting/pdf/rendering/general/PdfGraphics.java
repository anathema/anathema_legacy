package net.sf.anathema.character.reporting.pdf.rendering.general;

import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;

public class PdfGraphics {
  private final PdfContentByte directContent;
  private Bounds bounds;

  public PdfGraphics(PdfContentByte directContent, Bounds bounds) {
    this.directContent = directContent;
    this.bounds = bounds;
  }

  public PdfContentByte getDirectContent() {
    return directContent;
  }

  public Bounds getBounds() {
    return bounds;
  }

  public void setBounds(Bounds bounds) {
    this.bounds = bounds;
  }
}
