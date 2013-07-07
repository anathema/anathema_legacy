package net.sf.anathema.hero.sheet.pdf.encoder.graphics.shape;

import com.itextpdf.text.pdf.PdfContentByte;
import net.sf.anathema.hero.sheet.pdf.encoder.Position;

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
