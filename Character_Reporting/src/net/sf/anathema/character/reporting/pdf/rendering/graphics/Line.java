package net.sf.anathema.character.reporting.pdf.rendering.graphics;

import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.reporting.pdf.rendering.Position;

import java.awt.*;

public class Line {

  private PdfContentByte directContent;
  private final Position startPoint;
  private final Position endPoint;
  private  float lineWidth = 0f;

  public Line(PdfContentByte directContent, Position startPoint, Position endPoint) {
    this.directContent = directContent;
    this.startPoint = startPoint;
    this.endPoint = endPoint;
  }
  
  public void setLineWidth(float width) {
    this.lineWidth = width;
  }

  public void encode() {
    encode(Color.BLACK);
  }

  public void encode(Color color) {
    directContent.setColorFill(color);
    directContent.setLineWidth(lineWidth);
    directContent.moveTo(startPoint.x, startPoint.y);
    directContent.lineTo(endPoint.x, endPoint.y);
    directContent.stroke();
  }
}
