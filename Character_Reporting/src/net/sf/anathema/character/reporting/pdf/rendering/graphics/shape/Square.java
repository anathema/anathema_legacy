package net.sf.anathema.character.reporting.pdf.rendering.graphics.shape;

import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.reporting.pdf.rendering.Position;

public class Square implements IShape {

  private PdfContentByte directContent;
  private int dotSize;

  public Square(PdfContentByte directContent, int dotSize)    {
    this.directContent = directContent;
    this.dotSize = dotSize;
  }

  public void encode(Position lowerLeft, boolean filled) {
    directContent.rectangle(lowerLeft.x, lowerLeft.y, dotSize, dotSize);
    commitShape(filled);
  }

  private void commitShape(boolean isFilled) {
    if (isFilled) {
      directContent.fillStroke();
    }
    else {
      directContent.stroke();
    }
  }
}
