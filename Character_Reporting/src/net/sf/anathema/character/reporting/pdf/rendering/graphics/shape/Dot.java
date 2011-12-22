package net.sf.anathema.character.reporting.pdf.rendering.graphics.shape;

import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.reporting.pdf.rendering.Position;

public class Dot implements IShape {

  private PdfContentByte directContent;
  private int dotSize;

  public Dot(PdfContentByte directContent, int dotSize) {
    this.directContent = directContent;
    this.dotSize = dotSize;
  }

  public void encode(Position lowerLeft, boolean filled) {
    directContent.arc(lowerLeft.x, lowerLeft.y, lowerLeft.x + dotSize, lowerLeft.y + dotSize, 0, 360);
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
