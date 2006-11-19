package net.sf.anathema.character.reporting.sheet.util;

import net.sf.anathema.character.reporting.util.Position;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class PdfTraitEncoder extends AbstractPdfEncoder {

  private class Dot implements IShape {
    public void encode(PdfContentByte directContent, Position lowerLeft, int dotIndex, int value) {
      directContent.arc(lowerLeft.x, lowerLeft.y, lowerLeft.x + dotSize, lowerLeft.y + dotSize, 0, 360);
      commitShape(directContent, dotIndex < value);
    }
  }

  private interface IShape {
    public void encode(PdfContentByte directContent, Position lowerLeft, int dotIndex, int value);
  }

  private class Square implements IShape {
    public void encode(PdfContentByte directContent, Position lowerLeft, int dotIndex, int value) {
      directContent.rectangle(lowerLeft.x, lowerLeft.y, dotSize, dotSize);
      commitShape(directContent, dotIndex < value);
    }
  }

  private static final int SMALL_DOT_SPACING = 2;

  public static PdfTraitEncoder createLargeTraitEncoder(BaseFont baseFont) {
    return new PdfTraitEncoder(baseFont, 14, 10);
  }

  public static PdfTraitEncoder createMediumTraitEncoder(BaseFont baseFont) {
    return new PdfTraitEncoder(baseFont, 12, 8);
  }

  public static PdfTraitEncoder createSmallTraitEncoder(BaseFont baseFont) {
    return new PdfTraitEncoder(baseFont, 11, 6);
  }

  private final int height;
  private final int dotSize;
  private final BaseFont baseFont;

  private PdfTraitEncoder(BaseFont baseFont, int height, int dotSize) {
    this.baseFont = baseFont;
    this.height = height;
    this.dotSize = dotSize;
  }

  private void commitShape(PdfContentByte directContent, boolean isFilled) {
    if (isFilled) {
      directContent.fillStroke();
    }
    else {
      directContent.stroke();
    }
  }

  public int encodeDotsCenteredAndUngrouped(
      PdfContentByte directContent,
      Position position,
      float width,
      int value,
      int dotCount) {
    return encodeShapeCenteredAndUngrouped(directContent, position, width, value, dotCount, new Dot());
  }

  private int encodeGroupedDots(
      PdfContentByte directContent,
      Position position,
      float width,
      int value,
      int dotCount,
      final int dotSpacing) {
    int groupSpacing = dotCount > 5 ? dotSize / 2 : 0;
    int spacecount = (int) Math.floor((double) dotCount / 5);
    for (int dot = 0; dot < dotCount; dot++) {
      if (dot % 5 == 0) {
        spacecount--;
      }
      float currentGroupingSpace = spacecount * groupSpacing;
      float rightEdgeX = position.x + width;
      float spaceNeededRight = currentGroupingSpace + (dotCount - dot) * (dotSize + dotSpacing);
      Position lowerLeft = new Position(rightEdgeX - spaceNeededRight, position.y);
      new Dot().encode(directContent, lowerLeft, dot, value);
    }
    return dotCount * dotSize + (dotCount - 1) * dotSpacing + groupSpacing;
  }

  private int encodeShapeCenteredAndUngrouped(
      PdfContentByte directContent,
      Position position,
      float width,
      int value,
      int dotCount,
      IShape shape) {
    initDirectContent(directContent);
    int dotWidth = dotCount * dotSize;
    final float dotSpacing = (width - dotWidth) / (dotCount + 1);
    float neededWidth = dotWidth + (dotCount - 1) * dotSpacing;
    float leftDotX = position.x + (width - neededWidth) / 2;
    for (int dot = 0; dot < dotCount; dot++) {
      shape.encode(directContent, new Position(leftDotX, position.y), dot, value);
      leftDotX += dotSize + dotSpacing;
    }
    return height;
  }

  public int encodeSquaresCenteredAndUngrouped(
      PdfContentByte directContent,
      Position position,
      float width,
      int value,
      int dotCount) {
    return encodeShapeCenteredAndUngrouped(directContent, position, width, value, dotCount, new Square());
  }

  public int encodeWithLine(PdfContentByte directContent, Position position, float width, int value, int dotCount) {
    initDirectContent(directContent);
    float dotsWidth = encodeGroupedDots(directContent, position, width, value, dotCount, SMALL_DOT_SPACING);
    drawMissingTextLine(directContent, position, width - dotsWidth - 5);
    return height;
  }

  public int encodeWithText(
      PdfContentByte directContent,
      String text,
      Position position,
      float width,
      int value,
      int dotCount) {
    initDirectContent(directContent);
    directContent.beginText();
    directContent.showTextAligned(PdfContentByte.ALIGN_LEFT, text, position.x, position.y, 0);
    directContent.endText();
    encodeGroupedDots(directContent, position, width, value, dotCount, SMALL_DOT_SPACING);
    return height;
  }

  public int encodeWithTextAndRectangle(
      PdfContentByte directContent,
      String text,
      Position position,
      float width,
      int value,
      boolean favored,
      int dotCount) {
    initDirectContent(directContent);
    directContent.rectangle(position.x, position.y, dotSize, dotSize);
    commitShape(directContent, favored);
    int squareWidth = dotSize + 2;
    Position usualTraitPosition = new Position(position.x + squareWidth, position.y);
    return encodeWithText(directContent, text, usualTraitPosition, width - squareWidth, value, dotCount);
  }

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }

  public int getTraitHeight() {
    return height;
  }

  private void initDirectContent(PdfContentByte directContent) {
    setDefaultFont(directContent);
    setFillColorBlack(directContent);
    directContent.setLineWidth(0.8f);
  }
}