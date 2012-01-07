package net.sf.anathema.character.reporting.pdf.rendering.graphics.shape;

import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Position;

public class Dot extends AbstractShape {

  private int dotSize;

  public Dot(PdfContentByte directContent, int dotSize) {
    super(directContent);
    this.dotSize = dotSize;
  }

  protected void configureShape(Position lowerLeft) {
    float upperRightX = lowerLeft.x + dotSize;
    float upperRightY = lowerLeft.y + dotSize;
    getDirectContent().arc(lowerLeft.x, lowerLeft.y, upperRightX, upperRightY, 0, 360);
  }
}
