package net.sf.anathema.character.reporting.pdf.rendering.graphics;

import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;

import java.awt.*;

public class Box {

  private final Bounds bounds;
  private PdfContentByte directContent;

  public Box(Bounds bounds, PdfContentByte directContent) {
    this.bounds = bounds;
    this.directContent = directContent;
  }

  public void encode(float lineWidth) {
    directContent.setColorStroke(Color.BLACK);
    directContent.setLineWidth(lineWidth);
    directContent.rectangle(bounds.x, bounds.y, bounds.width, bounds.height);
    directContent.stroke();
  }

  public void encodeTotalType() {
    encode(0.75f);
  }
}
