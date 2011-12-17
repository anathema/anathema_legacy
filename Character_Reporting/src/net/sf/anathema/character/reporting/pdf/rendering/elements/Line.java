package net.sf.anathema.character.reporting.pdf.rendering.elements;

import com.lowagie.text.pdf.PdfContentByte;

import java.awt.*;

public class Line {

  public static Line createHorizontalByCoordinate(Position startPoint, float endX) {
    return new Line(startPoint, new Position(endX, startPoint.y));
  }

  public static Line createHorizontalByLength(Position startPoint, float length) {
    return createHorizontalByCoordinate(startPoint, startPoint.x + length);
  }

  public static Line createVerticalByLength(Position startPoint, float length) {
    return createVerticalByCoordinate(startPoint, startPoint.y + length);
  }

  public static Line createVerticalByCoordinate(Position startPoint, float endY) {
    return new Line(startPoint, new Position(startPoint.x, endY));
  }

  private final Position startPoint;
  private final Position endPoint;

  public Line(Position startPoint, Position endPoint) {
    this.startPoint = startPoint;
    this.endPoint = endPoint;
  }

  public void encode(PdfContentByte directContent) {
    encode(directContent, Color.BLACK);
  }

  public void encode(PdfContentByte directContent, Color color) {
    directContent.setColorFill(color);
    directContent.setLineWidth(0);
    directContent.moveTo(startPoint.x, startPoint.y);
    directContent.lineTo(endPoint.x, endPoint.y);
    directContent.stroke();
  }
}
