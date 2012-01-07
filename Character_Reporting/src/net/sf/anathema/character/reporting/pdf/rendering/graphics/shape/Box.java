package net.sf.anathema.character.reporting.pdf.rendering.graphics.shape;

import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;

import java.awt.*;

public class Box {

  private final Bounds bounds;
  private PdfContentByte directContent;

  public Box(Bounds bounds, PdfContentByte directContent) {
    this.bounds = bounds;
    this.directContent = directContent;
  }

  public void outline() {
    outline(0.8f);
  }

  public void outlineTotalType() {
    outline(0.75f);
  }

  public void outline(float lineWidth) {
    initializeGraphics(lineWidth);
    directContent.stroke();
  }

  public void fill() {
    initializeGraphics(0.8f);
    directContent.fillStroke();
  }

  private void initializeGraphics(float lineWidth) {
    directContent.setColorStroke(Color.BLACK);
    directContent.setLineWidth(lineWidth);
    directContent.rectangle(bounds.x, bounds.y, bounds.width, bounds.height);
  }
}
