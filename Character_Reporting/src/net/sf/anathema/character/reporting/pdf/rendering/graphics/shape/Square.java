package net.sf.anathema.character.reporting.pdf.rendering.graphics.shape;

import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.reporting.pdf.rendering.Position;

public class Square extends AbstractShape {

  private int dotSize;

  public Square(PdfContentByte directContent, int dotSize) {
    super(directContent);
    this.dotSize = dotSize;
  }

  @Override
  protected void configureShape(Position lowerLeft) {
    getDirectContent().rectangle(lowerLeft.x, lowerLeft.y, dotSize, dotSize);
  }
}
